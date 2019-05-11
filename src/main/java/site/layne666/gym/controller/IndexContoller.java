package site.layne666.gym.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.layne666.gym.pojo.Account;
import site.layne666.gym.pojo.ApiResult;
import site.layne666.gym.service.IAccountService;

import javax.servlet.http.HttpSession;

/**
 * 主页和登录
 *
 * @author layne666
 * @date 2019/04/27
 */
@Controller
public class IndexContoller {

    @Autowired
    private IAccountService accountService;

    @RequestMapping("/")
    public String index(HttpSession session){
        Object account = session.getAttribute("account");
        if(account!=null){
            return "index";
        }
        return "login";
    }

    @RequestMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/start")
    @ResponseBody
    public ApiResult login(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           HttpSession session){
        //校验用户名和登录密码不能为空
        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            Account account = accountService.checkLogin(username, password);
            if(account != null){
                session.setAttribute("account",account);
                return new ApiResult(true,"登录成功");
            }
            return new ApiResult(false,"登录失败");
        }
        return new ApiResult(false,"登录失败");
    }
}
