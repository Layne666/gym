package site.layne666.gym.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.layne666.gym.mapper.KsMapper;
import site.layne666.gym.mapper.UserMapper;
import site.layne666.gym.pojo.Course;
import site.layne666.gym.pojo.Ks;
import site.layne666.gym.pojo.User;
import site.layne666.gym.utils.UUIDUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            for (Ks ks : kss) {
                if(ks.getCourse()==null){
                    ks.setCourse(new Course());
                }
            }
            result.addAll(kss);
        }
        return result;
    }

    public void saveUserKs(Map<String,String> map){
        User user = packageUser(map);
        user.setBh(UUIDUtil.getUUid());
        userMapper.insertUser(user);
        Ks ks = new Ks();
        ks.setBh(UUIDUtil.getUUid());
        Course course = new Course();
        course.setBh(map.get("courseBh"));
        ks.setCourse(course);
        ks.setUser(user);
        ks.setSysks(map.get("sysks"));
        ks.setKsjg(map.get("ksjg"));
        ksMapper.insertKs(ks);
    }

    public void updateUserKs(Map<String,Object> map){
        Map<String,String> userMap = (Map<String, String>) map.get("user");
        User user = packageUser(userMap);
        user.setBh(userMap.get("bh"));
        userMapper.updateUser(user);
        Map<String,String> courseMap = (Map<String, String>) map.get("course");
        Course course = new Course();
        course.setBh(courseMap.get("bh"));
        Ks ks = new Ks();
        ks.setBh(String.valueOf(map.get("bh")));
        ks.setUser(user);
        ks.setCourse(course);
        ks.setSysks(String.valueOf(map.get("sysks")));
        ks.setKsjg(String.valueOf(map.get("ksjg")));
        ksMapper.updateKs(ks);
    }

    /**
     * 包装姓名、性别、年龄和联系方式
     * @param map
     * @return
     */
    public User packageUser(Map<String,String> map){
        User user = new User();
        user.setName(map.get("name"));
        user.setSex(map.get("sex"));
        user.setAge(map.get("age"));
        user.setTel(map.get("tel"));
        return user;
    }
}
