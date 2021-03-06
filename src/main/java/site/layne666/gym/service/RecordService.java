package site.layne666.gym.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.layne666.gym.common.Consts;
import site.layne666.gym.mapper.RecordMapper;
import site.layne666.gym.pojo.Account;
import site.layne666.gym.pojo.Course;
import site.layne666.gym.pojo.Ks;
import site.layne666.gym.pojo.Record;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<Record> getRecords(String name, HttpSession session){
        List<Record> records = new ArrayList<>();
        List<Ks> kss = ksService.getKss(name);
        Account account = (Account) session.getAttribute("account");
        for (Ks ks : kss) {
            List<Record> list ;
            if(Consts.YGLQX.equals(String.valueOf(account.getCoach().getZt()))){
                list = recordMapper.getRecords(ks.getBh(),null);
            }else {
                list = recordMapper.getRecords(ks.getBh(), account.getCoach().getBh());
            }
            for (Record record : list) {
                if(record.getKs().getCourse()==null){
                    record.getKs().setCourse(new Course("",""));
                }
            }
            records.addAll(list);
        }
        return records;
    }

    /**
     * 查询总金额
     * @param records
     * @return
     */
    public Integer getTotalPrice(List<Record> records){
        Integer totalPrice = 0;
        if(records.size()>0){
            for (Record record : records) {
                totalPrice += Integer.valueOf(record.getKszj());
            }
        }
        return totalPrice;
    }

    /**
     * 获取本月的课时记录
     * @return
     */
    public JSONObject getCurMonthRecords(){
        JSONObject result = new JSONObject();
        List<Record> records = recordMapper.getCurMonthRecords();
        Integer skks = 0;
        Integer kszj = 0;
        if(records.size()>0){
            for (Record record : records) {
                skks += Integer.valueOf(record.getSkks());
                kszj += Integer.valueOf(record.getKszj());
            }
        }
        result.put("skks",skks);
        result.put("kszj",kszj);
        result.put("records",dealRecords(records,kszj));
        return result;
    }

    /**
     * 根据教练给课时记录去重
     * @param records
     * @param zj 总价
     * @return
     */
    public List<JSONObject> dealRecords(List<Record> records,Integer zj){
        List<JSONObject> result = new ArrayList<>();
        Set<String> bhs = new HashSet<>();
        //计算效率
        DecimalFormat df = new DecimalFormat("0.00");
        for (Record record : records) {
            if(!bhs.contains(record.getCoach().getBh())){
                bhs.add(record.getCoach().getBh());
                JSONObject obj = new JSONObject();
                obj.put("bh",record.getCoach().getBh());
                obj.put("img",record.getCoach().getImg());
                obj.put("name",record.getCoach().getName());
                obj.put("skks",record.getSkks());
                obj.put("kszj",record.getKszj());
                obj.put("srzb",df.format(Double.valueOf(record.getKszj())*100/zj) + "%");
                result.add(obj);
            }else{
                for (JSONObject obj : result) {
                    if(record.getCoach().getBh().equals(obj.get("bh"))){
                        int skks = Integer.valueOf(String.valueOf(obj.get("skks"))) + Integer.valueOf(record.getSkks());
                        obj.put("skks",skks);
                        double kszj = Double.valueOf(String.valueOf(obj.get("kszj"))) + Double.valueOf(record.getKszj());
                        obj.put("kszj",kszj);
                        obj.put("srzb",df.format(kszj*100/zj) + "%");
                    }
                }
            }
        }
        return result;
    }
}
