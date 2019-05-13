package site.layne666.gym.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.layne666.gym.mapper.KsMapper;
import site.layne666.gym.mapper.UserMapper;
import site.layne666.gym.pojo.ApiResult;
import site.layne666.gym.pojo.Ks;
import site.layne666.gym.service.KsService;

/**
 * 用户（会员）
 *
 * @author layne666
 * @date 2019/04/28
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KsService ksService;

    @Autowired
    private KsMapper ksMapper;

    @GetMapping("")
    public String index(){
        return "user";
    }

    @PostMapping("")
    @ResponseBody
    public ApiResult indexPost(Integer pageNum,String name){
        if(pageNum==null){
            pageNum = 1;
        }
        try{
            PageHelper.startPage(pageNum, 10);
            PageInfo<Ks> pageInfo = new PageInfo<>(ksService.getKss(name));
            return new ApiResult(pageInfo);
        }catch (Exception e){
            log.error("查询用户课时失败",e);
            return new ApiResult(false,"查询用户课时失败");
        }
    }

    @RequestMapping("/add")
    public String add(){
        return "user-add";
    }
    @RequestMapping("/edit")
    public String edit(){
        return "user-edit";
    }

    @RequestMapping("/daka")
    public String daka(){
        return "user-daka";
    }
}
