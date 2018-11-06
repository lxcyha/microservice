package per.cyh.user.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by cyh on 2018/11/1.
 */
@Getter
@Setter
public class UserInfoDTO implements Serializable {

    private int id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String realName;
    private String token;


}
