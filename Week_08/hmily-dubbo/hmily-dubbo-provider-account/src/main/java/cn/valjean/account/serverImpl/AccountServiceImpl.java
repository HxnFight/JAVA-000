package cn.valjean.account.serverImpl;

import cn.valjean.common.entity.Account;
import cn.valjean.common.server.AccountService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0", tag = "red", weight = 100)
public class AccountServiceImpl implements AccountService {

    @Override
    public Account findAccountById(Integer id) {
        Account account = new Account();
        account.setId(id);
        account.setName("humily dubbo test user ");
        return account;
    }
}
