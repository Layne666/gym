package site.layne666.gym.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.layne666.gym.mapper.RecordMapper;
import site.layne666.gym.pojo.Ks;
import site.layne666.gym.pojo.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * @author layne666
 * @date 2019/05/20
 */
@Service
public class RecordService {

    @Autowired
    private KsService ksService;

    @Autowired
    private RecordMapper recordMapper;

    /**
     * 根据会员姓名查询课时记录
     * @param name
     * @return
     */
    public List<Record> getRecords(String name){
        List<Ks> kss = ksService.getKss(name);
        List<Record> records = new ArrayList<>();
        for (Ks ks : kss) {
            List<Record> list = recordMapper.getRecords(ks.getBh());
            records.addAll(list);
        }
        return records;
    }

    /**
     * 查询总金额
     * @param name
     * @return
     */
    public Integer getTotalPrice(String name){
        List<Ks> kss = ksService.getKss(name);
        Integer totalPrice = 0;
        for (Ks ks : kss) {
            List<Record> list = recordMapper.getRecords(ks.getBh());
            for (Record record : list) {
                totalPrice += Integer.valueOf(record.getKszj());
            }
        }
        return totalPrice;
    }
}
