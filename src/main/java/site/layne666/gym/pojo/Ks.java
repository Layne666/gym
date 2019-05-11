package site.layne666.gym.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 用户课时
 *
 * @author layne666
 * @date 2019/05/07
 */
@Data
public class Ks {
    /** 编号 **/
    private String bh;

    /** 剩余课时数 **/
    private String sysks;

    /** 课时价格 **/
    private String ksjg;

    /** 创建时间 **/
    private Date cjsj;

    /** 会员编号 **/
    private String userBh;

    /** 课时分类 **/
    private Course course;
}
