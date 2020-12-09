package cn.valjean.week08.service.impl;

import cn.valjean.week08.entity.TOrder;
import cn.valjean.week08.mapper.TOrderMapper;
import cn.valjean.week08.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author valjean
 * @since 2020-12-02
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Override
    public TOrder findOrderByIdPrimary(long id) {
        return this.getById(id);
    }

    @Override
    public TOrder findOrderByIdSecondary(long id) {
        return this.getById(id);
    }


}
