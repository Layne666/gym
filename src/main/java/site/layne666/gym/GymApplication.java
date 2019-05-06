package site.layne666.gym;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author layne666
 * @date 2019/05/05
 */
@SpringBootApplication
@MapperScan("site.layne666.gym.mapper")
public class GymApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymApplication.class, args);
    }

}
