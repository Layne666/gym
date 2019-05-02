package site.layne666.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author layne666
 * @date 2019/05/01
 */
@Controller
@RequestMapping("/center")
public class CenterController {
    @RequestMapping("/photo")
    public String center(){
        return "center-photo";
    }

    @RequestMapping("/data")
    public String data(){
        return "center-data";
    }


}
