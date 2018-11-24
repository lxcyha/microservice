package per.cyh.course.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import per.cyh.course.mapper.CourseMapper;
import per.cyh.course.thrift.ServiceProvider;
import per.cyh.dubbo.course.ICourseService;
import per.cyh.dubbo.course.dto.CourseDTO;
import per.cyh.thrift.user.UserInfo;
import per.cyh.thrift.user.dto.TeacherDTO;

import java.util.List;

/**
 * Created by cyh on 2018/11/11.
 */
@Service(interfaceClass = ICourseService.class)
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ServiceProvider serviceProvider;

    @Override
    public List<CourseDTO> courseList() {
        List<CourseDTO> courseDTOS = courseMapper.listCourse();

        if (courseDTOS != null) {
            for (CourseDTO courseDTO : courseDTOS) {
                Integer teacherId = courseMapper.getCourseTeacher(courseDTO.getId());

                if (teacherId != null) {
                    try {
                        UserInfo userInfo = serviceProvider.getUserService().getTeacherById(teacherId);
                        courseDTO.setTeacher(trans2Teacher(userInfo));
                    } catch (TException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return courseDTOS;
    }

    private TeacherDTO trans2Teacher(UserInfo userInfo) {
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(userInfo,teacherDTO);
        return teacherDTO;
    }
}
