package site.layne666.gym.pojo;


import lombok.Data;

/**
 * 教练
 *
 * @author layne666
 * @date 2019/05/07
 */
@Data
public class Coach {
    /** 编号 **/
    private String bh;

    /** 姓名 **/
    private String name;

    /** 性别 **/
    private String sex;

    /** 年龄 **/
    private String age;

    /** 电话号码 **/
    private String tel;

    /** 头像 **/
    private String img;

    /** 状态 **/
    private Integer zt;

}
