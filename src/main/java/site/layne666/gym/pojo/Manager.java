package site.layne666.gym.pojo;

import lombok.Data;

/**
 * 管理员
 *
 * @author layne666
 * @date 2019/05/06
 */
@Data
public class Manager {
    /** 管理员编号 **/
    private String bh;

    /** 管理员姓名 **/
    private String name;

    /** 登录信息 **/
    private Account account;
}
