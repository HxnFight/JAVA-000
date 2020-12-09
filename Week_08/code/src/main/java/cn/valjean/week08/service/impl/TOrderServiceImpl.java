package cn.valjean.week08.service.impl;

import cn.valjean.week08.entity.TOrder;
import cn.valjean.week08.mapper.TOrderMapper;
import cn.valjean.week08.service.TOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Resource
    private TOrderMapper tOrderMapper;

    @Override
    public TOrder findOrderByIdPrimary(long id) {
        return this.getById(id);
    }

    @Override
    public TOrder findOrderByIdSecondary(long id) {
        return this.getById(id);
    }

    @Override
    public int delOrderByTransId(String id) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("trans_id", id);
        return tOrderMapper.delete(wrapper);
    }


}
