package per.cyh.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import per.cyh.thrift.user.UserInfo;

/**
 * Created by cyh on 2018/11/3.
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo getUserInfoByUsername(String username);
}
