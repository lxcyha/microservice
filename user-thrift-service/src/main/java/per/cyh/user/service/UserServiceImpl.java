package per.cyh.user.service;

import org.apache.thrift.TException;
import per.cyh.thrift.user.UserInfo;
import per.cyh.thrift.user.UserService;

/**
 * Created by cyh on 2018/10/23.
 */
public class UserServiceImpl implements UserService.Iface {

    public UserInfo getUserById(int id) throws TException {
        return null;
    }

    public UserInfo getUserByName(String username) throws TException {
        return null;
    }

    public void registerUser(UserInfo userInfo) throws TException {

    }
}
