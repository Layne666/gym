package site.layne666.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 帮助
 *
 * @author layne666
 * @date 2019/05/03
 */
@Controller
@RequestMapping("/help")
public class HelpController {
    @RequestMapping("")
    public String help(){
        return "help";
    }
}
