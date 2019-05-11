package site.layne666.gym.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.layne666.gym.mapper.CoachMapper;
import site.layne666.gym.pojo.Coach;
import site.layne666.gym.service.ICoachService;

/**
 * @author layne666
 * @date 2019/05/12
 */
@Service
public class CoachServiceImpl implements ICoachService {

    @Autowired
    private CoachMapper coachMapper;

    @Override
    public void updateCoach(Coach coach) {
        Integer integer = coachMapper.updateCoach(coach);
        Integer aa = null;
    }
}
