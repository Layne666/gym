package site.layne666.gym.mapper;

import org.springframework.stereotype.Component;
import site.layne666.gym.pojo.Course;

import java.util.List;

/**
 * @author layne666
 * @date 2019/05/11
 */
@Component(value = "courseMapper")
public interface CourseMapper {

    /**
     * 查询所有课程分类
     * @return
     */
    List<Course> getCourses();

    /**
     * 根据编号查询课程分类
     * @param bh
     * @return
     */
    Course getCourseByBh(String bh);

    /**
     * 添加分类
     * @param course
     * @return
     */
    Integer insertCourse(Course course);

    /**
     * 根据编号删除分类
     * @param bh
     * @return
     */
    Integer deleteCourseByBH(String bh);

    /**
     * 更新分类
     * @param course
     * @return
     */
    Integer updateCourse(Course course);

}
