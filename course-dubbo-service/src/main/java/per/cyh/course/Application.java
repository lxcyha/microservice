package per.cyh.course;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by cyh on 2018/11/11.
 */
@SpringBootApplication
@EnableDubboConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
