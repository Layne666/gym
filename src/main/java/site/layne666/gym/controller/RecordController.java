package site.layne666.gym.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.layne666.gym.mapper.RecordMapper;
import site.layne666.gym.pojo.*;
import site.layne666.gym.service.RecordService;
import site.layne666.gym.utils.ExcelUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程记录
 *
 * @author layne666
 * @date 2019/05/03
 */
@Controller
@RequestMapping("/record")
@Slf4j
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private RecordMapper recordMapper;

    @GetMapping("")
    public String recordGet(){
        return "record";
    }

    @PostMapping("")
    @ResponseBody
    public ApiResult recordPost(Integer pageNum,String name){
        if(pageNum==null){
            pageNum = 1;
        }
        try{
            PageHelper.startPage(pageNum, 10);
            PageInfo<Record> pageInfo = new PageInfo<>(recordService.getRecords(name));
            JSONObject result = new JSONObject();
            result.put("pageInfo",pageInfo);
            result.put("totalPrice",recordService.getTotalPrice(name));
            return new ApiResult(result);
        }catch (Exception e){
            log.error("查询课时记录失败",e);
            return new ApiResult(false,"查询课时记录失败");
        }
    }

    @PostMapping("/del/{bh}")
    @ResponseBody
    public ApiResult del(@PathVariable("bh") String bh){
        try{
            recordMapper.deleteRecordByBh(bh);
            return new ApiResult(true,"课时记录删除成功");
        }catch (Exception e){
            log.error("课时记录删除失败",e);
            return new ApiResult(false,"课时记录删除失败");
        }

    }

    @PostMapping("/del")
    @ResponseBody
    public ApiResult batchDel(@RequestParam("bhs[]") String[] bhs){
        try {
            for (String bh : bhs) {
                recordMapper.deleteRecordByBh(bh);
            }
            return new ApiResult(true,"课时记录批量删除成功");
        }catch (Exception e){
            log.error("课时记录批量删除失败",e);
            return new ApiResult(true,"课时记录批量删除失败");
        }
    }

    @RequestMapping("/export")
    public void export(String name, HttpServletResponse resp) {
        String[] colTitles = {"会员姓名", "教练姓名", "课程分类", "课时价格（元）", "上课课时", "课时总价（元）", "创建时间"};
        String[] properties = {"userName", "coachName", "courseName", "ksjg", "skks", "kszj", "cjsj"};
        List<Object> list = new ArrayList<>();
        List<Record> records = recordService.getRecords(name);
        for (Record record : records) {
            RecordParam param = new RecordParam();
            param.setUserName(record.getKs().getUser().getName());
            param.setCoachName(record.getCoach().getName());
            param.setCourseName(record.getKs().getCourse().getName());
            param.setKsjg("¥"+record.getKs().getKsjg());
            param.setSkks(record.getSkks());
            param.setKszj("¥"+record.getKszj());
            param.setCjsj(record.getCjsj());
            list.add(param);
        }
        ExcelUtil.exportExcel(list, colTitles, properties, "打卡课时记录统计列表", "打卡课时记录统计表", resp);
    }
}
