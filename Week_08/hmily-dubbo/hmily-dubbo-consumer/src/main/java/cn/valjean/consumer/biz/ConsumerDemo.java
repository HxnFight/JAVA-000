package cn.valjean.consumer.biz;

import cn.valjean.common.entity.Account;
import cn.valjean.common.entity.Order;
import cn.valjean.common.entity.Inventory;
import cn.valjean.common.server.AccountService;
import cn.valjean.common.server.OrderService;
import cn.valjean.common.server.InventoryService;
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
    private InventoryService inventoryService;

    //, url = "dubbo://127.0.0.1:12345")
    @DubboReference(version = "1.0.0")
    private OrderService orderService;

    @DubboReference(version = "1.0.0")
    private AccountService accountService;


    @Bean
    public ApplicationRunner runner() {
        return args -> {
            Inventory inventory = inventoryService.queryInventoryById(1);
            log.info("find inventory --> info : {} ", JSON.toJSONString(inventory));

            Order order = orderService.findOrderById(1992129);
            log.info("find order --> info : {} ", JSON.toJSONString(order));

            Account account = accountService.findAccountById(1111);
            log.info("find account --> info : {} ", JSON.toJSONString(account));

        };
    }


}
