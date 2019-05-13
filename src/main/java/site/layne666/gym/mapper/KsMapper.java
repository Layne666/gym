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
     * 根据用户编号查询其课时信息
     * @param userBh
     * @return
     */
    List<Ks> getKssByUserBh(String userBh);

    Integer insertKs(Ks ks);

}
