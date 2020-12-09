package cn.valjean.week08.mapper;

import cn.valjean.week08.entity.TOrder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author valjean
 * @since 2020-12-02
 */
public interface TOrderMapper extends BaseMapper<TOrder> {

//    TOrder delete(@Param("wrapper") QueryWrapper<String> wrapper);
}
