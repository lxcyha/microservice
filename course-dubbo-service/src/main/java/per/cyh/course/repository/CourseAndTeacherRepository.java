package per.cyh.course.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import per.cyh.course.CourseAndTeacherRelation;
import per.cyh.course.CourseEntity;

/**
 * Created by cyh on 2018/11/3.
 */
public interface CourseAndTeacherRepository extends CrudRepository<CourseAndTeacherRelation, Integer> {

    Integer getUserIdByCourseId(@Param("courseId") int courseId);
}
