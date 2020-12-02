package cn.valjean.multipledb.controller;


import cn.valjean.multipledb.entity.TOrder;
import cn.valjean.multipledb.service.TOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author valjean
 * @since 2020-12-02
 */
@RestController
@RequestMapping("/t-order")
public class TOrderController {
    @Resource
    private TOrderService tOrderService;

    @GetMapping("/api")
    public String test() {
        TOrder tOrder = new TOrder();

        tOrder.setId(100L);
        tOrder.setTransId("42472893472347");
        tOrder.setOrderType(1);
        tOrder.setAmount(1.0);
        tOrder.setSaleType(1);
        tOrder.setGoodsId("sfsfs");
        tOrder.setPurchasePhone("sfs");
        tOrder.setPurchaseAddr("sfs");
        tOrder.setStatus(1);
        tOrder.setCreateTime(System.currentTimeMillis());
        tOrder.setUpdateTime(System.currentTimeMillis());

        boolean save = tOrderService.save(tOrder);
        System.out.println("save = " + save);

        TOrder torderById = tOrderService.getById(100);
        System.out.println("torderById = " + torderById);
        return "OK";
    }


}

