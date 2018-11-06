package per.cyh.user.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.cyh.thrift.user.UserInfo;
import per.cyh.user.dto.UserInfoDTO;
import per.cyh.user.redis.RedisClient;
import per.cyh.user.response.LoginResponse;
import per.cyh.user.response.Response;
import per.cyh.user.thrift.ServiceProvider;

import java.security.MessageDigest;
import java.util.Random;

/**
 * Created by cyh on 2018/10/26.
 */
@Controller
public class UserController {

    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private RedisClient redisClient;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestParam("username") String username,
                          @RequestParam("password") String password) {

        UserInfo userInfo;
        try {
            userInfo = serviceProvider.getUserService().getUserByName(username);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_OR_PASSWORD_INVALID;
        }

        if (userInfo == null) {
            return Response.USERNAME_OR_PASSWORD_INVALID;
        }

        if (!userInfo.getPassword().equals(md5(password))) {
            return Response.USERNAME_OR_PASSWORD_INVALID;
        }

        // create token
        String token = genToken();

        redisClient.set(token, toDTO(userInfo), 3600);

        return new LoginResponse(token);
    }

    @ResponseBody
    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    public Response sendVerifyCode(@RequestParam(value = "mobile", required = false) String mobile,
                                   @RequestParam(value = "email", required = false) String email) {
        String message = "Verify code is:";
        String code = randomCode("1234567890", 6);
        try {
            boolean result;
            if (StringUtils.isNotBlank(mobile)) {
                result = serviceProvider.getMessageService().sendMobileMessage(mobile, message + code);
                redisClient.set(mobile, code);
            } else if (StringUtils.isNotBlank(email)) {
                result = serviceProvider.getMessageService().sendEmailMessage(email, message + code);
                redisClient.set(email, code);
            } else {
                return Response.MOBILE_OR_EMAIL_REQUIRED;
            }

            if (!result) {
                return Response.SEND_VERIFY_CODE_FAILED;
            }
        } catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }

        return Response.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam(value = "mobile", required = false) String mobile,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "realName", required = false) String realName,
                             @RequestParam("verifyCode") String verifyCode) {

        if (StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if (StringUtils.isNotBlank(mobile)) {
            String redisCode = redisClient.get(mobile);
            if (!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        } else {
            String redisCode = redisClient.get(email);
            if (!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setMobile(mobile);
        userInfo.setPassword(md5(password));
        userInfo.setRealName(realName);
        userInfo.setEmail(email);

        try {
            serviceProvider.getUserService().registerUser(userInfo);
        } catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }

        return Response.SUCCESS;
    }

    public UserInfoDTO toDTO(UserInfo userInfo) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDTO);
        return userInfoDTO;
    }

    private String genToken() {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }

    private String randomCode(String s, int size) {

        StringBuilder result = new StringBuilder(size);

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }

    private String md5(String password) {
        try {
            MessageDigest md5MessageDigest = MessageDigest.getInstance("MD5");
            byte[] md5Binary = md5MessageDigest.digest(password.getBytes("utf-8"));
            return HexUtils.toHexString(md5Binary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
