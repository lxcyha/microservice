package per.cyh.user.service;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.cyh.thrift.user.UserInfo;
import per.cyh.thrift.user.UserService;
import per.cyh.user.repository.UserRepository;

/**
 * Created by cyh on 2018/10/23.
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    UserRepository userRepository;

    public UserInfo getUserById(int id) throws TException {
        return userRepository.getOne(id);
    }

    public UserInfo getUserByName(String username) throws TException {
        return userRepository.getUserInfoByUsername(username);
    }

    public void registerUser(UserInfo userInfo) throws TException {
        userRepository.save(userInfo);
    }
}
