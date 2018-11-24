package per.cyh.thrift.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by cyh on 2018/11/11.
 */
@Getter
@Setter
public class TeacherDTO extends UserDTO {

    private String intro;

    private String stars;
}
