package cn.valjean.multipledb.service.impl;

import cn.valjean.multipledb.config.CurDb;
import cn.valjean.multipledb.config.DbType;
import cn.valjean.multipledb.entity.TOrder;
import cn.valjean.multipledb.mapper.TOrderMapper;
import cn.valjean.multipledb.service.TOrderService;
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
    @CurDb(name = DbType.SECOND)
    public TOrder findOrderByIdSecondary(long id) {
        return this.getById(id);
    }


}
