package per.cyh.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import per.cyh.thrift.user.UserInfo;

/**
 * Created by cyh on 2018/10/25.
 */
@Mapper
@Component
public interface UserMapper {

    @Select("select id,username,password,real_name as realName,mobile,email from userinfo where id=#{id}")
    UserInfo getUserById(@Param("id") int id);

    @Select("select id,username,password,real_name as realName,mobile,email from userinfo where username=#{username}")
    UserInfo getUserByName(@Param("username") String username);

    @Insert("insert into userinfo (username, password, real_name, mobile, email) values (#{u.username}, #{u.password}, #{u.realName}, #{u.mobile}, #{u.email})")
    void registerUser(@Param("u") UserInfo userInfo);


}
