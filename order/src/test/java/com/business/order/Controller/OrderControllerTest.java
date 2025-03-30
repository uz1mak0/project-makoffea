package com.business.order.Controller;

import com.business.order.Entity.Orders;
import com.business.order.Services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class OrderControllerTest {

    @InjectMocks
    private OrderService orderService;

    @Test
    void saveOrders() {

        Orders orderDetails = new Orders();
        orderDetails.getOrderId();
        orderDetails.getVariation();
        orderDetails.getQuantity();
        orderDetails.getProductPrice();

        Optional<Orders> actualOrders = Optional.of(new Orders());

        assertTrue(actualOrders.isEmpty());
        assertEquals(orderDetails.getOrderId(), actualOrders.get().getOrderId());
        assertEquals(orderDetails.getVariation(), actualOrders.get().getVariation());
        assertEquals(orderDetails.getQuantity(), actualOrders.get().getQuantity());
        assertEquals(orderDetails.getProductPrice(), actualOrders.get().getProductPrice());
    }

//    @Test
//    void getOrdersById() {
//    }

    @Test
    void checkIfOrdersByIdExist() {

        Orders order = new Orders();

        Long orderId = (long) order.getOrderId();


        Optional<Orders> ordersDetails = Optional.ofNullable(orderService.getOrdersById(Math.toIntExact(orderId)));
        assertTrue(ordersDetails.isEmpty());
    }
}