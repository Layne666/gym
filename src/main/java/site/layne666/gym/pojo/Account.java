package site.layne666.gym.pojo;

import lombok.Data;

/**
 * 登录信息
 *
 * @author layne666
 * @date 2019/05/07
 */
@Data
public class Account {
    /** 编号 **/
    private String bh;

    /** 登录名 **/
    private String username;

    /** 登录密码 **/
    private String password;

    /** 教练信息 **/
    private Coach coach;
}
