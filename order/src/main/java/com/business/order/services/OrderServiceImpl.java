package com.business.order.services;

import com.business.order.entity.Orders;
import com.business.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepo;

    @Override
    public Orders saveOrders(Orders order) {
            orderRepo.save(order);

        return order;
    }

    @Override
    public Orders getOrdersById(int orderId) {

        Optional<Orders> checkOrders = orderRepo.findById(orderId);

        Orders order;

        if(checkOrders.isPresent()) {
            order = checkOrders.get();
        }else {
            throw new RuntimeException("Product with ID, " + orderId + "has not found");
        }

        return order;
    }

    @Override
    public int getTotalPrices(int orderId) {
        Optional<Orders> orders = orderRepo.findById(orderId);

        Orders obj = new Orders();
        if (orders.isEmpty()){
            throw new RuntimeException("Orders with the id, " + orderId + "not found");
        }else {
            return obj.getQuantity() * obj.getProductPrice();
        }
    }
}
