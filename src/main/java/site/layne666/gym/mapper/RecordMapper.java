package site.layne666.gym.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import site.layne666.gym.pojo.Record;

import java.util.List;

/**
 * @author layne666
 * @date 2019/05/20
 */
@Component(value = "recordMapper")
public interface RecordMapper {

    /**
     * 根据课时编号查询所有会员信息
     * @param ksBh
     * @param coachBh
     * @return
     */
    List<Record> getRecords(@Param("ksBh") String ksBh, @Param("coachBh") String coachBh);

    /**
     * 添加记录
     * @param record
     * @return
     */
    Integer InsertRecord(Record record);

    /**
     * 根据编号删除课时记录
     * @param bh
     * @return
     */
    Integer deleteRecordByBh(String bh);

    /**
     * 获取本月的课时记录
     * @return
     */
    List<Record> getCurMonthRecords();


}
