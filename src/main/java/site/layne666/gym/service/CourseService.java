package site.layne666.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.layne666.gym.mapper.CourseMapper;
import site.layne666.gym.pojo.Course;
import site.layne666.gym.utils.UUIDUtil;

/**
 * @author layne666
 * @date 2019/05/12
 */
@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public Integer addCourse(String name){
        Course course = new Course();
        course.setBh(UUIDUtil.getUUid());
        course.setName(name);
        return courseMapper.insertCourse(course);
    }
}
