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

    public static  final  Response USERNAME_OR_PASSWORD_INVALID = new Response("1001","username or password invalid");

    public static  final  Response REGISTER_ERROR = new Response("1002","register error");

    public static  final  Response SUCCESS = new Response("0","success");

    private String code;
    private String message;
}
