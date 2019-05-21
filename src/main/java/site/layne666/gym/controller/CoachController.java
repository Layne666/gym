package site.layne666.gym.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.layne666.gym.mapper.AccountMapper;
import site.layne666.gym.mapper.CoachMapper;
import site.layne666.gym.pojo.Account;
import site.layne666.gym.pojo.ApiResult;
import site.layne666.gym.pojo.Coach;
import site.layne666.gym.pojo.CoachParam;
import site.layne666.gym.service.CoachService;
import site.layne666.gym.utils.ExcelUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 教练
 *
 * @author layne666
 * @date 2019/05/02
 */
@Controller
@RequestMapping("/coach")
@Slf4j
public class CoachController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private CoachMapper coachMapper;

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping("")
    public  String coachGet(){
        return "coach";
    }

    @PostMapping("")
    @ResponseBody
    public ApiResult coachPost(Integer pageNum,String name){
        if(pageNum==null){
            pageNum = 1;
        }
        try{
            PageHelper.startPage(pageNum, 10);
            PageInfo<Account> pageInfo = new PageInfo<>(coachService.getCoachAccountInfo(name));
            return new ApiResult(pageInfo);
        }catch (Exception e){
            log.error("查询用户课时失败",e);
            return new ApiResult(false,"查询用户课时失败");
        }
    }

    @GetMapping("/add")
    public String addGet(){
        return "coach-add";
    }

    @PostMapping("/add")
    @ResponseBody
    public ApiResult addPost(CoachParam param){
        try{
            coachService.saveCoachAccount(param);
            return new ApiResult(true,"添加教练信息成功");
        }catch (Exception e){
            log.error("添加教练信息失败",e);
            return new ApiResult(false,"添加教练信息失败");
        }
    }

    @GetMapping("/edit")
    public String editGet(){
        return "coach-edit";
    }

    @RequestMapping("/edit/{bh}")
    @ResponseBody
    public ApiResult edit(@PathVariable("bh") String bh){
        try{
            Account account = accountMapper.getAccountByBh(bh);
            return new ApiResult(account);
        }catch (Exception e){
            log.error("教练账户信息查询失败",e);
            return new ApiResult(false,"教练账户信息查询失败");
        }
    }

    @PostMapping("/edit")
    @ResponseBody
    public ApiResult editPost(@RequestBody Account account){
        try{
            coachService.updateCoachAccount(account);
            return new ApiResult(true,"修改教练账户信息成功");
        }catch (Exception e){
            log.error("修改教练账户信息失败",e);
            return new ApiResult( false,"修改教练账户信息失败");
        }
    }

    @PostMapping("/del/{bh}")
    @ResponseBody
    public ApiResult del(@PathVariable("bh") String bh){
        try{
            coachService.deleteCoachAccount(bh);
            return new ApiResult(true,"教练账户信息删除成功");
        }catch (Exception e){
            log.error("教练账户信息删除失败",e);
            return new ApiResult(false,"教练账户信息删除失败");
        }

    }

    @PostMapping("/del")
    @ResponseBody
    public ApiResult batchDel(@RequestParam("bhs[]") String[] bhs){
        try {
            for (String bh : bhs) {
                coachService.deleteCoachAccount(bh);
            }
            return new ApiResult(true,"教练账户信息批量删除成功");
        }catch (Exception e){
            log.error("教练账户信息批量删除失败",e);
            return new ApiResult(true,"教练账户信息批量删除失败");
        }
    }

    @RequestMapping("/export")
    public void export(String name, HttpServletResponse resp){
        String[] colTitles = {"姓名","性别","年龄","联系方式","登录名","登录密码"};
        String[] properties ={"name","sex","age","tel","username","password"};
        List<Account> accounts = coachService.getCoachAccountInfo(name);
        List<Object> list = new ArrayList<>();
        for (Account account : accounts) {
            CoachParam param = new CoachParam();
            Coach coach = account.getCoach();
            param.setName(coach.getName());
            param.setSex(coach.getSex());
            param.setAge(coach.getAge());
            param.setTel(coach.getTel());
            param.setUsername(account.getUsername());
            //不暴露密码
            param.setPassword("******");
            list.add(param);
        }
        ExcelUtil.exportExcel(list, colTitles, properties, "教练账户信息统计列表", "教练账户信息统计表", resp);
    }

    @RequestMapping("/count")
    @ResponseBody
    public ApiResult count(){
        try{
            Integer result = coachMapper.countCoachs();
            return new ApiResult(result);
        }catch (Exception e){
            log.error("查询教练数量失败",e);
            return new ApiResult(false,"查询教练数量失败");
        }
    }
}
