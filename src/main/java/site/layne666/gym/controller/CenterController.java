package site.layne666.gym.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import site.layne666.gym.mapper.AccountMapper;
import site.layne666.gym.mapper.CoachMapper;
import site.layne666.gym.pojo.Account;
import site.layne666.gym.pojo.Coach;
import site.layne666.gym.pojo.CoachParam;
import site.layne666.gym.service.AccountService;
import site.layne666.gym.utils.HttpRequestUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 个人中心
 *
 * @author layne666
 * @date 2019/05/01
 */
@Controller
@RequestMapping("/center")
@Slf4j
public class CenterController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private CoachMapper coachMapper;

    @Value("${uploadPath}")
    private String uploadPath;

    @GetMapping("/photo")
    public String photoGet(){
        return "center-photo";
    }

    @RequestMapping("/data")
    public String data(){
        return "center-data";
    }

    @PostMapping("/data/edit")
    public void edit(CoachParam param, HttpSession session, HttpServletResponse resp) throws IOException {
        Account account = (Account)session.getAttribute("account");
        accountService.updateAccount(account, param);
        //获取更新后的账号信息
        Account newAccount = accountMapper.getAccountByBh(account.getBh());
        session.setAttribute("account",newAccount);
        resp.sendRedirect("/center/data");
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam(value = "file") MultipartFile upload, HttpSession session) {
        try {
            String str = HttpRequestUtil.uploadFile(upload);
            JSONObject jsonObject = JSONObject.parseObject(str);
            String url = jsonObject.getJSONObject("data").getString("url");
            Account account = (Account) session.getAttribute("account");
            Coach coach = account.getCoach();
            coach.setImg(url);
            //更新数据库
            coachMapper.updateCoach(coach);
            //更新session
            Account newAccount = accountMapper.getAccountByBh(account.getBh());
            session.setAttribute("account", newAccount);
        } catch (Exception e) {
            log.error("头像上传失败", e);
        }
        return "redirect:/center/photo";
    }
}
