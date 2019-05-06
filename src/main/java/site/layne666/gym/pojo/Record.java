package site.layne666.gym.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 记录
 *
 * @author layne666
 * @date 2019/05/07
 */
@Data
public class Record {

    /** 记录编号 **/
    private String bh;

    /** 上课课时 **/
    private String skks;

    /** 课时总价 **/
    private String kszj;

    /** 创建时间 **/
    private Date cjsj;

    /** 用户信息 **/
    private User user;

    /** 教练信息 **/
    private Coach coach;
}
