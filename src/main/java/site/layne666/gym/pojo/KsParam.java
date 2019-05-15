package site.layne666.gym.pojo;

import lombok.Data;

/**
 * @author layne666
 * @date 2019/05/16
 */
@Data
public class KsParam {

    /** 编号 **/
    private String bh;

    /** 剩余上课时 **/
    private String sysks;

    /** 课时价格 **/
    private String ksjg;

    /** 创建时间 **/
    private String cjsj;

    private String userName;

    private String userSex;

    private String userAge;

    private String userTel;

    private String courseName;
}
