package per.cyh.course.controller;

import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import per.cyh.dubbo.course.ICourseService;
import per.cyh.dubbo.course.dto.CourseDTO;
import per.cyh.thrift.user.dto.UserInfoDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by cyh on 2018/11/18.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Reference
    private ICourseService courseService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<CourseDTO> courseList(HttpServletRequest request) {

        UserInfoDTO userInfoDTO = (UserInfoDTO) request.getAttribute("UserInfoDTO");

        List<CourseDTO> userInfoDTOS = courseService.courseList();
        System.out.println(userInfoDTOS);
        System.out.println(userInfoDTO);
        return  userInfoDTOS;
    }

}
