package site.layne666.gym.pojo;

import lombok.Data;

/**
 * @author layne666
 * @date 2019/05/11
 */
@Data
public class CoachParam {

    /** 姓名 **/
    private String name;

    /** 性别 **/
    private String sex;

    /** 年龄 **/
    private String age;

    /** 电话号码 **/
    private String tel;

    /** 登录名 **/
    private String username;

    /** 登录密码 **/
    private String password;
}
