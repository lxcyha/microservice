package per.cyh.user;

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
@Table(name = "pe_teacher")
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String intro;
    private int stars;

}
