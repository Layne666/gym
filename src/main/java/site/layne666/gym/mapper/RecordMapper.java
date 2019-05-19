package site.layne666.gym.mapper;

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
     * 查询所有会员信息
     * @return
     */
    List<Record> getRecords();

    /**
     * 添加记录
     * @param record
     * @return
     */
    Integer InsertRecord(Record record);


}
