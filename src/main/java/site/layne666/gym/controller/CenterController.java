package site.layne666.gym.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.layne666.gym.common.Consts;
import site.layne666.gym.mapper.AccountMapper;
import site.layne666.gym.mapper.CoachMapper;
import site.layne666.gym.pojo.Account;
import site.layne666.gym.pojo.ApiResult;
import site.layne666.gym.pojo.Coach;
import site.layne666.gym.pojo.CoachParam;
import site.layne666.gym.service.AccountService;
import site.layne666.gym.utils.UUIDUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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

    @PostMapping("/photo")
    @ResponseBody
    public ApiResult photoPost(HttpSession session){
        Account account = (Account)session.getAttribute("account");
        Coach coach = account.getCoach();
        String path = coach.getImg();
        return new ApiResult(path);
    }

    @PostMapping("/photo/path")
    @ResponseBody
    public ApiResult path(String path, HttpSession session){
        try{
            Account account = (Account)session.getAttribute("account");
            Coach coach = account.getCoach();
            coach.setImg(path);
            //更新数据库
            coachMapper.updateCoach(coach);
            //更新session
            Account newAccount = accountMapper.getAccountByBh(account.getBh());
            session.setAttribute("account",newAccount);
            return new ApiResult(true,"头像上传成功");
        }catch (Exception e){
            log.error("头像上传失败",e);
            return new ApiResult(true,"头像上传失败");
        }
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
    @ResponseBody
    public ApiResult upload(@RequestParam(value = "file") MultipartFile upload, HttpSession session, HttpServletResponse resp) {
        try{
            //要上传的文件路径
            String path = uploadPath;
            File file = new File(path);
            //获取原文件名
            String originalFilename = upload.getOriginalFilename();
            //获取原文件的后缀名
            String suffix  = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //筛选jpg和png
            if(Consts.IMG_JPG.equals(suffix) || Consts.IMG_PNG.equals(suffix)){
                Account account = ((Account) session.getAttribute("account"));
                String img = UUIDUtil.getUUid();
                String filename = img + "." + suffix;
                //上传
                upload.transferTo(new File(file,filename));
                Coach coach = account.getCoach();
                //若之前的头像不是系统头像，则进行删除
                if(!StringUtils.equals(Consts.IMG_DAFAULT,coach.getImg())){
                    //删除旧图片
                    new File(coach.getImg()).delete();
                }
                //设置新的图片路径
                coach.setImg(path + File.separator + filename);
                //更新数据库
                coachMapper.updateCoach(coach);
                //更新session
                Account newAccount = accountMapper.getAccountByBh(account.getBh());
                session.setAttribute("account",newAccount);
                resp.sendRedirect("/center/photo");
                return new ApiResult(true,"头像上传成功");
            }
            return new ApiResult(false,"头像上传失败");
        }catch (IOException e){
            log.error("头像上传失败",e);
            return new ApiResult(false,"头像上传失败");
        }
    }

}
