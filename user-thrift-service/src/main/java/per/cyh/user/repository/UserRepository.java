package per.cyh.user.repository;

import org.springframework.data.repository.CrudRepository;
import per.cyh.user.UserInfoEntity;

/**
 * Created by cyh on 2018/11/3.
 */
public interface UserRepository extends CrudRepository<UserInfoEntity, Integer> {

    UserInfoEntity findUserInfoEntityByUsername(String username);
}
