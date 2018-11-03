package per.cyh.user.service;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.cyh.thrift.user.UserInfo;
import per.cyh.thrift.user.UserService;
import per.cyh.user.UserInfoEntity;
import per.cyh.user.repository.UserRepository;

/**
 * Created by cyh on 2018/10/23.
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    UserRepository userRepository;

    public UserInfo getUserById(int id) throws TException {
        return toThriftModel(userRepository.getOne(id));
    }

    public UserInfo getUserByName(String username) throws TException {
        return toThriftModel(userRepository.findByUsername(username));
    }

    public void registerUser(UserInfo userInfo) throws TException {
        userRepository.save(toPojo(userInfo));
    }

    private UserInfoEntity toPojo(UserInfo userInfo) {

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        try {
            PropertyUtils.copyProperties(userInfoEntity, userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userInfoEntity;
    }

    private UserInfo toThriftModel(UserInfoEntity userInfoEntity) {

        UserInfo userInfo = new UserInfo();
        try {
            PropertyUtils.copyProperties(userInfo, userInfoEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return userInfo;
        }
        return userInfo;
    }


}
