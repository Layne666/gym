package site.layne666.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户（会员）
 *
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

    @RequestMapping("/add")
    public String add(){
        return "user-add";
    }
    @RequestMapping("/edit")
    public String edit(){
        return "user-edit";
    }

    @RequestMapping("/daka")
    public String daka(){
        return "user-daka";
    }
}
