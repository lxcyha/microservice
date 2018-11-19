package per.cyh.course.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import per.cyh.course.CourseEntity;
import per.cyh.course.repository.CourseAndTeacherRepository;
import per.cyh.course.repository.CourseRepository;
import per.cyh.dubbo.course.ICourseService;
import per.cyh.dubbo.course.dto.CourseDTO;

import java.util.List;

/**
 * Created by cyh on 2018/11/11.
 */
@Service(interfaceClass = ICourseService.class)
@Component
public class CourseServiceImpl implements ICourseService {

    @Autowired
    CourseAndTeacherRepository courseAndTeacherRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<CourseDTO> courseList() {
        Iterable<CourseEntity> courseEntities = courseRepository.findAll();
        if (courseEntities != null) {
            for (CourseEntity courseEntity: courseEntities){
                Integer teacherId = courseAndTeacherRepository.getUserIdByCourseId(courseEntity.getId());

                if (teacherId != null){

                }
            }
        }
        return null;
    }
}
