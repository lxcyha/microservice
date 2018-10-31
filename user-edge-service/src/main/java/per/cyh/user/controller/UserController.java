package per.cyh.user.controller;

import org.apache.thrift.TException;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.cyh.thrift.user.UserInfo;
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
    private RedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestParam("username") String username,
                          @RequestParam("password") String password) {

        UserInfo userInfo = null;
        try {
            userInfo = serviceProvider.getUserService().getUserByName(username);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_OR_PASSWORD_INVALID;
        }

        if (userInfo == null) {
            return Response.USERNAME_OR_PASSWORD_INVALID;
        }

        if (!md5(password).equals(userInfo.getPassword())) {
            return Response.USERNAME_OR_PASSWORD_INVALID;
        }

        // create token
        String token = genToken();

        redisTemplate.opsForValue().set(userInfo.getId(), token);

        return Response.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(UserInfo userInfo) {

        try {
            serviceProvider.getUserService().registerUser(userInfo);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_OR_PASSWORD_INVALID;
        }

        if (userInfo == null) {
            return Response.USERNAME_OR_PASSWORD_INVALID;
        }

//        if (!userInfo.getPassword().equals(password)) {
//            return Response.USERNAME_OR_PASSWORD_INVALID;
//        }

        // create token
        String token = genToken();

        redisTemplate.opsForValue().set(userInfo.getId(), token);

        return Response.USERNAME_OR_PASSWORD_INVALID;
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
