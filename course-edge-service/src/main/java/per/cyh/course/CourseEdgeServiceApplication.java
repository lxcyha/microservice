package per.cyh.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import per.cyh.course.filter.CourseFilter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cyh on 2018/10/25.
 */
    @SpringBootApplication
    public class CourseEdgeServiceApplication {

        public static void main(String[] args) {
            SpringApplication.run(CourseEdgeServiceApplication.class, args);
        }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CourseFilter());

        List<String> urlPattern = new ArrayList<>();
        urlPattern.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPattern);
        return filterRegistrationBean;
    }
}
