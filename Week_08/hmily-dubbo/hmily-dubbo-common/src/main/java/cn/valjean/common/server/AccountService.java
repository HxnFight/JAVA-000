package cn.valjean.common.server;

import cn.valjean.common.entity.Account;

public interface AccountService {
    Account findAccountById(Integer id);
}
