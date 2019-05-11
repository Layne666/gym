package site.layne666.gym.mapper;

import site.layne666.gym.pojo.Ks;

import java.util.List;

/**
 * @author layne666
 * @date 2019/05/11
 */
public interface KsMapper {

    /**
     * 根据用户编号查询其课时信息
     * @param userBh
     * @return
     */
    List<Ks> getKssByUserBh(String userBh);

    Integer insertKs(Ks ks);

}
