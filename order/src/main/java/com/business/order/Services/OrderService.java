package com.business.order.Services;

import com.business.order.Entity.Orders;


public interface OrderService {

    Orders saveOrders(Orders order);
    Orders getOrdersById(int orderId);
    int getTotalPrice(int orderId);
}
