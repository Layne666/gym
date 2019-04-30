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

    @RequestMapping("/edit")
    public String edit(){
        return "edit";
    }

    @RequestMapping("/daka")
    public String daka(){
        return "daka";
    }
}
