package per.cyh.user;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by cyh on 2018/11/3.
 */
@Getter
@Setter
@Entity
@Table(name = "pe_userinfo")
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String realName;

    @NotNull
    private String password;
    @NotNull
    private String username;

    private String email;
    @NotNull
    private String mobile;
}
