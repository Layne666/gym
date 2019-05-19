package site.layne666.gym.service;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.layne666.gym.mapper.AccountMapper;
import site.layne666.gym.mapper.CoachMapper;
import site.layne666.gym.pojo.Account;
import site.layne666.gym.pojo.Coach;
import site.layne666.gym.pojo.CoachParam;
import site.layne666.gym.utils.MD5Util;

import java.util.List;

/**
 * @author layne666
 * @date 2019/05/11
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private CoachMapper coachMapper;

    /**
     * 检测是否登录信息是否存在，并返回
     * @param username
     * @param password
     * @return
     */
    public Account checkLogin(String username, String password) {
        String pwd = MD5Util.MD5(password);
        List<Account> list = accountMapper.getAccountByNameAndPwd(username, pwd);
        if(list.size() == 1){
            return list.get(0);
        }
        return null;
    }

    /**
     * 更新账户信息
     * @param account
     * @param param
     * @return
     */
    public void updateAccount(Account account, CoachParam param) {
        account.setUsername(param.getUsername());
        //若修改了密码，需要对新密码进行MD5加密
        if(!StringUtils.equals(account.getPassword(),param.getPassword())){
            account.setPassword(MD5Util.MD5(param.getPassword()));
        }
        Coach coach = account.getCoach();
        coach.setName(param.getName());
        coach.setSex(param.getSex());
        coach.setAge(param.getAge());
        coach.setTel(param.getTel());
        coachMapper.updateCoach(coach);
        accountMapper.updateAccount(account);
    }


}
