package site.layne666.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.layne666.gym.mapper.CoachMapper;

/**
 * @author layne666
 * @date 2019/05/12
 */
@Service
public class CoachService {

    @Autowired
    private CoachMapper coachMapper;

}
