package site.layne666.gym.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.layne666.gym.mapper.RecordMapper;
import site.layne666.gym.pojo.ApiResult;
import site.layne666.gym.pojo.Ks;
import site.layne666.gym.pojo.Record;
import site.layne666.gym.service.RecordService;

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
    public ApiResult recordPost(Integer pageNum,String name,String courseBh){
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
}
