package cn.valjean.common.server;

import cn.valjean.common.entity.Order;

public interface OrderService {
   Order findOrderById(Integer id);
}
