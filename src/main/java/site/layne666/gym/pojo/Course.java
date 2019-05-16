package site.layne666.gym.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程分类
 *
 * @author layne666
 * @date 2019/05/07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    /** 分类编号 **/
    private String bh;

    /** 分类名称 **/
    private String name;
}
