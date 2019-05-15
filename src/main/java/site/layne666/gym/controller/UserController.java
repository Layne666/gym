package site.layne666.gym.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.layne666.gym.mapper.KsMapper;
import site.layne666.gym.pojo.ApiResult;
import site.layne666.gym.pojo.Ks;
import site.layne666.gym.pojo.KsParam;
import site.layne666.gym.service.KsService;
import site.layne666.gym.utils.ExcelUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private KsMapper ksMapper;

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
            log.error("添加用户课时失败",e);
            return new ApiResult(false,"添加用户课时失败");
        }
    }

    @GetMapping("/edit")
    public String editGet(){
        return "user-edit";
    }

    @RequestMapping("/edit/{bh}")
    @ResponseBody
    public ApiResult edit(@PathVariable("bh") String bh){
        try{
            Ks ks = ksMapper.getKssByBh(bh);
            return new ApiResult(ks);
        }catch (Exception e){
            log.error("分类查询失败",e);
            return new ApiResult(false,"分类查询失败");
        }
    }

    @PostMapping("/edit")
    @ResponseBody
    public ApiResult editPost(@RequestBody Map<String,Object> map){
        try{
            ksService.updateUserKs(map);
            return new ApiResult(true,"修改用户课时成功");
        }catch (Exception e){
            log.error("修改用户课时失败",e);
            return new ApiResult(false,"修改用户课时失败");
        }
    }

    @RequestMapping("/daka")
    public String daka(){
        return "user-daka";
    }

    @RequestMapping("/export")
    public void export(String name,HttpServletResponse resp){
        String[] colTitles = {"姓名","性别","年龄","联系方式","剩余上课时","课时价格（元）","课程分类","加入时间"};
        String[] properties ={"userName","userSex","userAge","userTel","sysks","ksjg","courseName","cjsj"};
        List<Ks> kss = ksService.getKss(name);
        List<Object> list = new ArrayList<>();
        for (Ks ks : kss) {
            KsParam ksParam = new KsParam();
            ksParam.setBh(ks.getBh());
            ksParam.setSysks(ks.getSysks());
            ksParam.setKsjg("¥"+ks.getKsjg());
            ksParam.setCjsj(ks.getCjsj());
            ksParam.setCourseName(ks.getCourse().getName());
            ksParam.setUserName(ks.getUser().getName());
            ksParam.setUserAge(ks.getUser().getAge());
            ksParam.setUserSex(ks.getUser().getSex());
            ksParam.setUserTel(ks.getUser().getTel());
            list.add(ksParam);
        }
        Map<String, Object> result = ExcelUtil.exportExcel(list, colTitles, properties, "会员课时统计列表", "会员课时统计表", resp);
    }
}
