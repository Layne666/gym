package site.layne666.gym.mapper;


import site.layne666.gym.pojo.Manager;

import java.util.List;

/**
 * @author layne666
 * @date 2019/05/05
 */
public interface ManagerMapper {

    /**
     * 查询所有管理员信息
     * @return manager集合
     */
    List<Manager> getManagers();

    /**
     * 新增管理员
     * @param manager
     * @return
     */
    Integer insertManager(Manager manager);

    /**
     * 删除管理员
     * @param bh 编号
     * @return
     */
    Integer deleteManager(String bh);

}
