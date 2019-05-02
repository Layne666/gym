package site.layne666.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 教练
 *
 * @author layne666
 * @date 2019/05/02
 */
@Controller
@RequestMapping("/coach")
public class CoachController {

    @RequestMapping("")
    public  String coach(){
        return "coach";
    }
}
