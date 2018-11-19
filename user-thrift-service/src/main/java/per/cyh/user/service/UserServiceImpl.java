package per.cyh.user.service;

import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.cyh.thrift.user.UserInfo;
import per.cyh.thrift.user.UserService;
import per.cyh.user.UserInfoEntity;
import per.cyh.user.repository.TeacherRepository;
import per.cyh.user.repository.UserRepository;

/**
 * Created by cyh on 2018/10/23.
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TeacherRepository teacherRepository;

    public UserInfo getUserById(int id) throws TException {

        return toThriftModel(userRepository.findById(id).get());
    }

    public UserInfo getUserByName(String username) throws TException {
        return toThriftModel(userRepository.findUserInfoEntityByUsername(username));
    }

    @Override
    public UserInfo getTeacherById(int id) throws TException {
        return toThriftModel(teacherRepository.findTeacherEntityById(id));
    }

    public void registerUser(UserInfo userInfo) throws TException {
        userRepository.save(toPojo(userInfo));
    }

    private UserInfoEntity toPojo(UserInfo userInfo) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        BeanUtils.copyProperties(userInfo, userInfoEntity);
        return userInfoEntity;
    }

    private UserInfo toThriftModel(UserInfoEntity userInfoEntity) {
        if (userInfoEntity == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoEntity, userInfo);
        return userInfo;
    }


}
