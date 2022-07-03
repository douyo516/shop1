package com.cqut.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqut.store.entity.Order;
import com.cqut.store.entity.OrderItem;

/** 处理订单及订单商品数据的持久层接口 */
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);
}
