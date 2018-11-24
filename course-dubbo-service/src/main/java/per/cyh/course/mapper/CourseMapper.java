package per.cyh.course.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import per.cyh.dubbo.course.dto.CourseDTO;

import java.util.List;

/**
 * Created by cyh on 2018/11/24.
 */
@Mapper
public interface CourseMapper {

    @Select("select * from pe_course")
    List<CourseDTO> listCourse();

    @Select("select user_id from pr_user_course where course_id=#{courseId}")
    Integer getCourseTeacher(@Param("courseId") int courseId);
}
