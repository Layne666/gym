package site.layne666.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 课程
 *
 * @author layne666
 * @date 2019/05/03
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @RequestMapping("")
    public String course(){
        return "course";
    }
}
