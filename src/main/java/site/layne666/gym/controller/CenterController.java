package site.layne666.gym.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.layne666.gym.mapper.AccountMapper;
import site.layne666.gym.mapper.CoachMapper;
import site.layne666.gym.pojo.Account;
import site.layne666.gym.pojo.ApiResult;
import site.layne666.gym.pojo.Coach;
import site.layne666.gym.service.AccountService;
import site.layne666.gym.utils.HttpRequestUtil;

import javax.servlet.http.HttpSession;

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

    @GetMapping("/photo")
    public String photoGet(){
        return "center-photo";
    }

    @GetMapping("/data")
    public String dataGet(){
        return "center-data";
    }

    @PostMapping("/data")
    @ResponseBody
    public ApiResult dataPost(HttpSession session){
        Account account = (Account)session.getAttribute("account");
        return new ApiResult(account);
    }

    @RequestMapping("/data/edit")
    @ResponseBody
    public ApiResult edit(@RequestBody Account account, HttpSession session) {
        try{
            boolean result = accountService.updateAccount(account);
            if(result){
                //获取更新后的账号信息
                Account newAccount = accountMapper.getAccountByBh(account.getBh());
                session.setAttribute("account",newAccount);
                return new ApiResult(true,"修改个人信息成功！");
            }
            return new ApiResult(false,"登录名已存在，请重新输入！");
        }catch (Exception e){
            log.error("修改个人信息失败",e);
            return new ApiResult(false,"修改个人信息失败");
        }
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
