package site.layne666.gym.service;


import site.layne666.gym.pojo.Coach;

/**
 * @author layne666
 * @date 2019/05/11
 */
public interface ICoachService {

    /**
     * 更新教练信息
     * @param coach
     */
    void updateCoach(Coach coach);
}
