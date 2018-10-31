package per.cyh.user.response;



import java.io.Serializable;

/**
 * Created by cyh on 2018/10/26.
 */
public class Response implements Serializable {

    public static  final  Response USERNAME_OR_PASSWORD_INVALID = new Response("1001","username or password invalid");
    public static  final  Response SUCCESS = new Response("0","success");

    private String code;
    private String message;

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
