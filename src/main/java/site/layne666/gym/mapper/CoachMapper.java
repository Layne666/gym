package site.layne666.gym.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import site.layne666.gym.pojo.Coach;

import java.util.List;

/**
 * @author layne666
 * @date 2019/05/05
 */
@Component(value = "coachMapper")
public interface CoachMapper {

    /**
     * 查询所有教练（非管理员）信息
     * @param name
     * @return Coach集合
     */
    List<Coach> getCoaches(@Param("name") String name);

    /**
     * 根据编号查询教练信息
     * @param bh
     * @return
     */
    Coach getCoachByBH(String bh);

    /**
     * 新增教练信息
     * @param Coach
     * @return
     */
    Integer insertCoach(Coach Coach);

    /**
     * 删除教练信息
     * @param bh 编号
     * @return
     */
    Integer deleteCoachByBh(String bh);

    /**
     * 修改教练信息
     * @param Coach
     * @return
     */
    Integer updateCoach(Coach Coach);

}
