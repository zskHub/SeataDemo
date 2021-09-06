package com.zsk.seata.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author jianjun.ren
 * @since 2021/02/16
 */
@TableName("order_info")
@Data
@Accessors(chain = true)
public class Order {

    @TableId
    private Long id;

    private Long userId;

    private Long productId;

    private int count;

    private BigDecimal money;

    private int status;
}
