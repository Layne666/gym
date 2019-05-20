package site.layne666.gym.mapper;

import org.springframework.stereotype.Component;
import site.layne666.gym.pojo.Ks;

import java.util.List;

/**
 * @author layne666
 * @date 2019/05/11
 */
@Component(value = "ksMapper")
public interface KsMapper {

    /**
     * 查询所有课时信息
     * @return
     */
    List<Ks> getKss();

    /**
     * 根据编号查询所有课时信息
     * @param bh
     * @return
     */
    Ks getKssByBh(String bh);

    /**
     * 根据课程编号查询所有课时信息
     * @param courseBh
     * @return
     */
    List<Ks> getKssByCourseBh(String courseBh);

    /**
     * 根据用户编号查询其课时信息
     * @param userBh
     * @return
     */
    List<Ks> getKssByUserBh(String userBh);

    /**
     * 添加用户课时
     * @param ks
     * @return
     */
    Integer insertKs(Ks ks);

    /**
     * 更新用户课时信息
     * @param ks
     * @return
     */
    Integer updateKs(Ks ks);

    /**
     * 根据编号删除用户课时信息
     * @param bh
     * @return
     */
    Integer deleteKsByBh(String bh);

}
