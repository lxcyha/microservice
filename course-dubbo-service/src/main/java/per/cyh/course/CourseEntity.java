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
@Table(name = "pe_course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;

}
