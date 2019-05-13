package site.layne666.gym.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.layne666.gym.mapper.CourseMapper;
import site.layne666.gym.pojo.ApiResult;
import site.layne666.gym.pojo.Course;
import site.layne666.gym.service.CourseService;

import java.util.List;

/**
 * 课程
 *
 * @author layne666
 * @date 2019/05/03
 */
@Controller
@RequestMapping("/course")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMapper courseMapper;

    @GetMapping("")
    public String courseGet(){
        return "course";
    }

    @PostMapping("")
    @ResponseBody
    public ApiResult coursePost(Integer pageNum,String name){
        if(pageNum==null){
            pageNum = 1;
        }
        try{
            PageHelper.startPage(pageNum, 10);
            PageInfo<Course> pageInfo = new PageInfo<>(courseMapper.getCourses(name));
            return new ApiResult(pageInfo);
        }catch (Exception e){
            log.error("查询分类失败",e);
            return new ApiResult(false,"查询分类失败");
        }
    }

    @PostMapping("/list")
    @ResponseBody
    public ApiResult list(){
        try{
            List<Course> courses = courseMapper.getCourses(null);
            return new ApiResult(courses);
        }catch (Exception e){
            log.error("查询分类失败",e);
            return new ApiResult(false,"查询分类失败");
        }
    }


    @GetMapping("/add")
    public String addGet(){
        return "course-add";
    }

    @PostMapping("/add")
    @ResponseBody
    public ApiResult addPost(@RequestParam("name") String name){
        Integer result = courseService.addCourse(name);
        if(result == 1){
            return new ApiResult(true,"添加分类成功");
        }
        return new ApiResult(false,"添加分类失败");
    }

    @GetMapping("/edit")
    public String edit(){
        return "course-edit";
    }

    @GetMapping("/edit/{bh}")
    @ResponseBody
    public ApiResult editGet(@PathVariable("bh") String bh){
        try{
            Course course = courseMapper.getCourseByBh(bh);
            return new ApiResult(course);
        }catch (Exception e){
            log.error("分类查询失败",e);
            return new ApiResult(false,"分类查询失败");
        }
    }

    @PostMapping("/edit")
    @ResponseBody
    public ApiResult editPost(Course course){
        Integer result = courseMapper.updateCourse(course);
        if(result == 1){
            return new ApiResult(true,"分类修改成功");
        }
        return new ApiResult(false,"分类修改失败");
    }

    @PostMapping("/del/{bh}")
    @ResponseBody
    public ApiResult del(@PathVariable("bh") String bh){
        Integer result = courseMapper.deleteCourseByBH(bh);
        if(result == 1){
            return new ApiResult(true,"分类删除成功");
        }
        return new ApiResult(false,"分类删除失败");
    }

    @PostMapping("/del")
    @ResponseBody
    public ApiResult batchDel(@RequestParam("bhs[]") String[] bhs){
        try {
            for (String bh : bhs) {
                courseMapper.deleteCourseByBH(bh);
            }
            return new ApiResult(true,"分类批量删除成功");
        }catch (Exception e){
            log.error("分类批量删除成功",e);
            return new ApiResult(true,"分类批量删除成功");
        }
    }
}
