package cn.valjean.week08.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author valjean
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    private String transId;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 订单金额
     */
    private Double amount;

    /**
     * 促销类型 【1:无促销，2:大促,3: and so on ]
     */
    private Integer saleType;

    /**
     * 商品ids
     */
    private String goodsId;

    /**
     * 购买人联系方式
     */
    private String purchasePhone;

    /**
     * 购买人地址
     */
    private String purchaseAddr;

    /**
     * 订单状态【1:有效,2:无效,3: 发货】
     */
    private Integer status;

    /**
     * 创建时间（毫秒值）
     */
    private Long createTime;

    /**
     * 更新时间（毫秒值）
     */
    private Date updateTime;


}
