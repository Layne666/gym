package site.layne666.gym.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.layne666.gym.pojo.ApiResult;

import javax.servlet.http.HttpSession;

/**
 * 主页和登录
 *
 * @author layne666
 * @date 2019/04/27
 */
@Controller
public class IndexContoller {

    @RequestMapping("/")
    public String index(HttpSession session){
        Object loginname = session.getAttribute("loginname");
        if(loginname!=null){
            return "index";
        }
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ApiResult login(@RequestParam("loginname") String loginname,
                           @RequestParam("password") String password,
                           HttpSession session){
        if(StringUtils.isNotBlank(loginname) && StringUtils.isNotBlank(password)){
            session.setAttribute("loginname",loginname);
            return new ApiResult(true,"登录成功");
        }
        return new ApiResult(false,"登录失败");
    }
}
