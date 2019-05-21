package site.layne666.gym.service;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.layne666.gym.mapper.AccountMapper;
import site.layne666.gym.mapper.CoachMapper;
import site.layne666.gym.pojo.Account;
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
     * 更新账户信息并返回
     * @param param
     * @return
     */
    public boolean updateAccount(Account param) {
        //确保登录名唯一
        Account count = accountMapper.getAccountByName(param.getUsername());
        if(count==null || StringUtils.equals(count.getBh(),param.getBh())){
            Account oldAccount = accountMapper.getAccountByBh(param.getBh());
            //若修改了密码，需要对新密码进行MD5加密
            if(!StringUtils.equals(param.getPassword(),oldAccount.getPassword())){
                param.setPassword(MD5Util.MD5(param.getPassword()));
            }
            coachMapper.updateCoach(param.getCoach());
            accountMapper.updateAccount(param);
            return true;
        }else{
            return false;
        }
    }

}
