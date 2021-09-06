package com.zsk.seata.order.service;

import com.zsk.seata.order.entity.Order;

/**
 * @author jianjun.ren
 * @since 2021/02/16
 */
public interface OrderService {

    boolean create(Order order);
}
