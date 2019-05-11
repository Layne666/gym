package site.layne666.gym.controller;

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

    @RequestMapping("")
    public String course(){
        return "course";
    }

    @RequestMapping("/list")
    @ResponseBody
    public ApiResult list(){
        try{
            List<Course> courses = courseMapper.getCourses();
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
}
