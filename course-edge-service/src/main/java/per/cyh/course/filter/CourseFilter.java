package per.cyh.course.filter;

import per.cyh.thrift.user.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cyh on 2018/11/18.
 */
public class CourseFilter extends per.cyh.user.client.LoginFilter {
    @Override
    protected void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, UserDTO userDTO) {

        httpServletRequest.setAttribute("UserDTO", userDTO);

    }
}
