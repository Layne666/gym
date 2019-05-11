package site.layne666.gym.mapper;

import site.layne666.gym.pojo.User;

import java.util.List;

/**
 * @author layne666
 * @date 2019/05/11
 */
public interface UserMapper {

    /**
     * 查询所有会员信息
     * @return
     */
    List<User> getUsers();

    /**
     * 根据会员编号查询会员信息
     * @param bh
     * @return
     */
    User getUserByBH(String bh);

    /**
     * 添加会员信息
     * @param user
     * @return
     */
    Integer insertUser(User user);

}