package per.cyh.thrift.user.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by cyh on 2018/11/1.
 */
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {

    private int id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String realName;
}
