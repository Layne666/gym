package site.layne666.gym.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.layne666.gym.pojo.ApiResult;
import site.layne666.gym.pojo.Ks;
import site.layne666.gym.service.KsService;

import java.util.Map;

/**
 * 用户（会员）
 *
 * @author layne666
 * @date 2019/04/28
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private KsService ksService;

    @GetMapping("")
    public String index(){
        return "user";
    }

    @PostMapping("")
    @ResponseBody
    public ApiResult indexPost(Integer pageNum,String name){
        if(pageNum==null){
            pageNum = 1;
        }
        try{
            PageHelper.startPage(pageNum, 10);
            PageInfo<Ks> pageInfo = new PageInfo<>(ksService.getKss(name));
            return new ApiResult(pageInfo);
        }catch (Exception e){
            log.error("查询用户课时失败",e);
            return new ApiResult(false,"查询用户课时失败");
        }
    }

    @GetMapping("/add")
    public String addGet(){
        return "user-add";
    }

    @PostMapping("/add")
    @ResponseBody
    public ApiResult addPost(@RequestBody Map<String,String> map){
        try{
            ksService.saveUserKs(map);
            return new ApiResult(true,"添加用户课时成功");
        }catch (Exception e){
            log.error("查询用户课时失败",e);
            return new ApiResult(false,"添加用户课时失败");
        }
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
