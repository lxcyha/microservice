package per.cyh.course;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import per.cyh.dubbo.course.CourseService;
import per.cyh.dubbo.course.dto.CourseDTO;

import java.util.List;

/**
 * Created by cyh on 2018/11/11.
 */
@Service(interfaceClass = CourseService.class)
@Component
public class CourseServiceImpl implements CourseService {


    @Override
    public List<CourseDTO> courseList() {
        return null;
    }
}
