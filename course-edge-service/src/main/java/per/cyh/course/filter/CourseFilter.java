package per.cyh.course.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import per.cyh.thrift.user.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cyh on 2018/11/18.
 */
@Component
public class CourseFilter extends per.cyh.user.client.LoginFilter {
    @Value("${user.edge.service.addr}")
    private String userEdgeServiceAddr;
    @Override
    protected String userEdgeServiceAddr() {
        return userEdgeServiceAddr;
    }

    @Override
    protected void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, UserDTO userDTO) {

        httpServletRequest.setAttribute("UserDTO", userDTO);

    }
}
