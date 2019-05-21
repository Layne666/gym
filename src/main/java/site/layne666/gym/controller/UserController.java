package site.layne666.gym.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.layne666.gym.mapper.KsMapper;
import site.layne666.gym.mapper.RecordMapper;
import site.layne666.gym.mapper.UserMapper;
import site.layne666.gym.pojo.*;
import site.layne666.gym.service.KsService;
import site.layne666.gym.utils.ExcelUtil;
import site.layne666.gym.utils.UUIDUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RecordMapper recordMapper;

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
            if(ks.getCourse()==null){
                ks.setCourse(new Course("",""));
            }
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

    @PostMapping("/del/{bh}")
    @ResponseBody
    public ApiResult del(@PathVariable("bh") String bh){
        try{
            Ks ks = ksMapper.getKssByBh(bh);
            //List<Ks> kss = ksMapper.getKssByUserBh(ks.getUser().getBh());
            //如果当前用户只有一个课时信息就一起删除
            //if(kss.size()==1){
                userMapper.deleteUserByBh(ks.getUser().getBh());
            //}
            ksMapper.deleteKsByBh(bh);
            return new ApiResult(true,"用户课时删除成功");
        }catch (Exception e){
            log.error("用户课时删除失败",e);
            return new ApiResult(false,"用户课时删除失败");
        }

    }

    @PostMapping("/del")
    @ResponseBody
    public ApiResult batchDel(@RequestParam("bhs[]") String[] bhs){
        try {
            for (String bh : bhs) {
                Ks ks = ksMapper.getKssByBh(bh);
                userMapper.deleteUserByBh(ks.getUser().getBh());
                ksMapper.deleteKsByBh(bh);
            }
            return new ApiResult(true,"用户课时批量删除成功");
        }catch (Exception e){
            log.error("用户课时批量删除失败",e);
            return new ApiResult(true,"用户课时批量删除失败");
        }
    }

    @GetMapping("/daka")
    public String dakaGet(){
        return "user-daka";
    }

    @PostMapping("/daka")
    @ResponseBody
    public ApiResult dakaPost(String bh, String dkks, HttpSession session){
        try{
            Account account = (Account)session.getAttribute("account");
            Ks ks = ksMapper.getKssByBh(bh);
            //减去已上课时
            ks.setSysks(String.valueOf(Integer.valueOf(ks.getSysks())-Integer.valueOf(dkks)));
            ksMapper.updateKs(ks);
            Record record = new Record();
            record.setBh(UUIDUtil.getUUid());
            record.setSkks(dkks);
            record.setKszj(String.valueOf(Integer.valueOf(ks.getKsjg())*Integer.valueOf(dkks)));
            record.setKs(ks);
            record.setCoach(account.getCoach());
            recordMapper.InsertRecord(record);
            return new ApiResult(true,"打卡成功");
        }catch (Exception e){
            log.error("打卡失败",e);
            return new ApiResult(false,"打卡失败");
        }
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
        ExcelUtil.exportExcel(list, colTitles, properties, "会员课时统计列表", "会员课时统计表", resp);
    }

    @RequestMapping("/count")
    @ResponseBody
    public ApiResult count(){
        try{
            Integer result = userMapper.countUsers();
            return new ApiResult(result);
        }catch (Exception e){
            log.error("查询会员数量失败",e);
            return new ApiResult(false,"查询会员数量失败");
        }
    }
}
