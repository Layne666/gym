package site.layne666.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.layne666.gym.mapper.AccountMapper;
import site.layne666.gym.mapper.CoachMapper;
import site.layne666.gym.pojo.Account;
import site.layne666.gym.pojo.Coach;
import site.layne666.gym.pojo.CoachParam;
import site.layne666.gym.utils.MD5Util;
import site.layne666.gym.utils.UUIDUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author layne666
 * @date 2019/05/12
 */
@Service
public class CoachService {

    @Autowired
    private CoachMapper coachMapper;

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 根据教练姓名模糊查询教练账户信息
     * @param name
     * @return
     */
    public List<Account> getCoachAccountInfo(String name){
        List<Coach> coaches = coachMapper.getCoaches(name);
        List<Account> accounts = new ArrayList<>();
        for (Coach coach : coaches) {
            Account account = accountMapper.getAccountByCoachBh(coach.getBh());
            //不返回真实密码
            account.setPassword("******");
            accounts.add(account);
        }
        return accounts;
    }

    /**
     * 添加教练账号信息
     * @param param
     */
    public void saveCoachAccount(CoachParam param){
        Coach coach = new Coach();
        coach.setBh(UUIDUtil.getUUid());
        coach.setName(param.getName());
        coach.setSex(param.getSex());
        coach.setAge(param.getAge());
        coach.setTel(param.getTel());
        coachMapper.insertCoach(coach);
        Account account = new Account();
        account.setBh(UUIDUtil.getUUid());
        account.setCoach(coach);
        account.setUsername(param.getUsername());
        //密码进行MD5加密
        account.setPassword(MD5Util.MD5(param.getPassword()));
        accountMapper.insertAccount(account);
    }

    /**
     * 根据教练删除教练账户信息
     * @param coachBh
     */
    public void deleteCoachAccount(String coachBh){
        coachMapper.deleteCoachByBh(coachBh);
        accountMapper.deleteAccountByCoachBH(coachBh);
    }

}
