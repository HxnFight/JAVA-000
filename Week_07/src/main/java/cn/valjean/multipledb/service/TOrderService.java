package cn.valjean.multipledb.service;

import cn.valjean.multipledb.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author valjean
 * @since 2020-12-02
 */
public interface TOrderService extends IService<TOrder> {

    TOrder findOrderByIdPrimary(long id);

    TOrder findOrderByIdSecondary(long id);
}
