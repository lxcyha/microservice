package per.cyh.thrift.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by cyh on 2018/11/11.
 */
public class TeacherDTO extends UserInfoDTO {

    private String intro;

    private int stars;
}
