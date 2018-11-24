package per.cyh.user.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by cyh on 2018/11/3.
 */
@Getter
@Setter
public class LoginResponse extends Response {

    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}
