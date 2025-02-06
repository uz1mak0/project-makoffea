package com.business.order.services;

import com.business.order.entity.Orders;


public interface OrderService {

    Orders saveOrders(Orders order);
    Orders getOrdersById(int orderId);
    int getTotalPrice(int orderId);
}
