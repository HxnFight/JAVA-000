package cn.valjean.provider.serverImpl;

import cn.valjean.common.entity.Order;
import cn.valjean.common.server.OrderService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0", tag = "red", weight = 100)
public class OrderServiceImpl implements OrderService {
    @Override
    public Order findOrderById(Integer id) {
        Order order = new Order();
        order.setId(id);
        order.setPrice(11.00);
        order.setGoodsName("humily dubbo goods ");
        return order;
    }
}
