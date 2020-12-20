package cn.valjean.common.server;

import cn.valjean.common.entity.Order;
import cn.valjean.common.entity.User;

public interface Userservice {
    User findUserById(Integer id);
}
