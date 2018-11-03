package per.cyh.user.service;

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
        return toThriftModel(userRepository.findById(id).get());
    }

    public UserInfo getUserByName(String username) throws TException {
        return toThriftModel(userRepository.findUserInfoEntityByUsername(username));
    }

    public void registerUser(UserInfo userInfo) throws TException {
        userRepository.save(toPojo(userInfo));
    }

    private UserInfoEntity toPojo(UserInfo userInfo) {

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setPassword(userInfo.getPassword());
        userInfoEntity.setEmail(userInfo.getEmail());
        userInfoEntity.setId(userInfo.getId());
        userInfoEntity.setRealName(userInfo.getRealName());
        userInfoEntity.setMobile(userInfo.getMobile());
        return userInfoEntity;
    }

    private UserInfo toThriftModel(UserInfoEntity userInfoEntity) {

        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(userInfoEntity.getPassword());
        userInfo.setEmail(userInfoEntity.getEmail());
        userInfo.setId(userInfoEntity.getId());
        userInfo.setRealName(userInfoEntity.getRealName());
        userInfo.setMobile(userInfoEntity.getMobile());
        return userInfo;
    }


}
