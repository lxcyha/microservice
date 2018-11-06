package per.cyh.user.response;

/**
 * Created by cyh on 2018/11/3.
 */
public class LoginResponse extends Response {

    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}
