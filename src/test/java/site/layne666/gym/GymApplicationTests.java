package site.layne666.gym;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.layne666.gym.mapper.ManagerMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GymApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    ManagerMapper mapper;

    @Test
    public void test(){
        System.out.println(mapper.getManagers());
    }
}
