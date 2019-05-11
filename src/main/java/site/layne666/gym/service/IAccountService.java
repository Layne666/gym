package site.layne666.gym.service;

import site.layne666.gym.pojo.Account;
import site.layne666.gym.pojo.CoachParam;

/**
 * @author layne666
 * @date 2019/05/11
 */
public interface IAccountService {

    /**
     * 检测是否登录信息是否存在，并返回
     * @param username
     * @param password
     * @return
     */
    Account checkLogin(String username, String password);

    /**
     * 根据编号查询账号信息
     * @param bh
     * @return
     */
    Account getAccountByBh(String bh);

    /**
     * 更新账户信息
     * @param account
     * @param param
     * @return
     */
    void updateAccount(Account account, CoachParam param);

}
