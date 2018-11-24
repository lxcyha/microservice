package per.cyh.user.service;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.cyh.thrift.user.UserInfo;
import per.cyh.thrift.user.UserService;
import per.cyh.user.mapper.UserMapper;

/**
 * Created by cyh on 2018/10/23.
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    UserMapper userMapper;

    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    public UserInfo getUserByName(String username) throws TException {
        return userMapper.getUserByName(username);
    }

    public UserInfo getTeacherById(int id) throws TException {
        UserInfo userInfo = userMapper.getTeacherById(id);
        return userInfo;
    }

    public void registerUser(UserInfo userInfo) throws TException {
        userMapper.registerUser(userInfo);
    }
}
