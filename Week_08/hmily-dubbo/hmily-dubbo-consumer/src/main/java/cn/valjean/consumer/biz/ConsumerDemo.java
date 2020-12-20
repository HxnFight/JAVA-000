package cn.valjean.consumer.biz;

import cn.valjean.common.entity.Order;
import cn.valjean.common.entity.User;
import cn.valjean.common.server.OrderService;
import cn.valjean.common.server.Userservice;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerDemo {

    //, url = "dubbo://127.0.0.1:12345")
    @DubboReference(version = "1.0.0")
    private Userservice userService;

    //, url = "dubbo://127.0.0.1:12345")
    @DubboReference(version = "1.0.0")
    private OrderService orderService;

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            User user = userService.findUserById(1);
            System.out.println("find user id=1 from server: " + user.getName());
            Order order = orderService.findOrderById(1992129);
            log.info("find order --> info : {} ", JSON.toJSONString(order));
        };
    }


}
