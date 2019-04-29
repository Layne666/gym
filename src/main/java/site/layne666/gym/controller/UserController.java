package site.layne666.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author layne666
 * @date 2019/04/28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("")
    public String index(){
        return "user";
    }
}
