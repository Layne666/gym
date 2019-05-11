package site.layne666.gym.mapper;

import org.apache.ibatis.annotations.Param;
import site.layne666.gym.pojo.Account;

import java.util.List;

/**
 * @author layne666
 * @date 2019/05/11
 */
public interface AccountMapper {

    /**
     * 查询所有登录信息
     * @return Account集合
     */
    List<Account> getAccounts();

    /**
     * 根据用户名和密码查询登录信息
     * @param username
     * @param password
     * @return
     */
    List<Account> getAccountByNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询登录信息个数
     *
     * @param username 用户名
     * @return Account对象集合
     */
    Integer countAccountByName(String username);

    /**
     * 添加登录信息
     * @param account
     * @return
     */
    Integer insertAccount(Account account);

    /**
     * 根据教练编号删除其登录信息
     * @param coachBh
     * @return
     */
    Integer deleteAccountByCoachBH(String coachBh);

    /**
     * 更新登录信息（用户名和登录密码）
     * @param account
     * @return
     */
    Integer updateAccount(Account account);
}
