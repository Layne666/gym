package site.layne666.gym.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.layne666.gym.mapper.KsMapper;
import site.layne666.gym.mapper.UserMapper;
import site.layne666.gym.pojo.Ks;
import site.layne666.gym.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author layne666
 * @date 2019/05/13
 */
@Service
public class KsService {

    @Autowired
    private KsMapper ksMapper;

    @Autowired
    private UserMapper userMapper;

    public List<Ks> getKss(String name){
        List<User> users = userMapper.getUsers(name);
        List<Ks> result = new ArrayList<>();
        for (User user : users) {
            List<Ks> kss = ksMapper.getKssByUserBh(user.getBh());
            result.addAll(kss);
        }
        return result;
    }
}
