package per.cyh.course;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by cyh on 2018/11/11.
 */
@Entity
@Setter
@Getter
@ToString
@Table(name = "pr_user_course")
public class CourseAndTeacherRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer courseId;
    private Integer userId;
}
