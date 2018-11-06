package per.cyh.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by cyh on 2018/10/26.
 */
@Getter
@Setter
@AllArgsConstructor
public class Response implements Serializable {

    public static final Response USERNAME_OR_PASSWORD_INVALID = new Response("1001", "username or password invalid");

    public static final Response MOBILE_OR_EMAIL_REQUIRED = new Response("1002", "mobile or email required");
    public static final Response SEND_VERIFY_CODE_FAILED = new Response("1003", "send verify code failed");
    public static final Response VERIFY_CODE_INVALID = new Response("1004", "verify code invalid");

    public static final Response SUCCESS = new Response("0", "success");

    private String code;
    private String message;

    public Response() {
        this.code = "0";
        this.message = "success";
    }

    public static Response exception(Exception e){
        return new Response("9999", e.getMessage());
    }

}
