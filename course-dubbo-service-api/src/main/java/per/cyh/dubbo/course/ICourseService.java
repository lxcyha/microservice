package per.cyh.dubbo.course;

import per.cyh.dubbo.course.dto.CourseDTO;

import java.util.List;

/**
 * Created by cyh on 2018/11/10.
 */
public interface ICourseService {

    List<CourseDTO> courseList();
}
