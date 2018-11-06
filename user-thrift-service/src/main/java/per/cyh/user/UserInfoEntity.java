package per.cyh.user;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by cyh on 2018/11/3.
 */
@Entity
@Setter
@Getter
@ToString
@Table(name = "pe_userinfo")
public class UserInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String realName;
    private String password;
    private String username;
    private String email;
    private String mobile;
}
