package site.layne666.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 课程记录
 *
 * @author layne666
 * @date 2019/05/03
 */
@Controller
@RequestMapping("/record")
public class RecordController {
    @RequestMapping("")
    public String record(){
        return "record";
    }
}
