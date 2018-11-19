package per.cyh.dubbo.course.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.cyh.thrift.user.dto.TeacherDTO;

import java.io.Serializable;

/**
 * Created by cyh on 2018/11/10.
 */
@Getter
@Setter
@ToString
public class CourseDTO implements Serializable{

    private int id;
    private String title;
    private String description;
    private TeacherDTO teacher;
}
