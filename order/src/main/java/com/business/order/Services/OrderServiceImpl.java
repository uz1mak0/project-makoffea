package com.business.order.Services;

import com.business.order.ExceptionHandler.TotalPriceException;
import com.business.order.Entity.Orders;
import com.business.order.Repository.OrderRepository;
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
    public int getTotalPrice(int orderId) {
        Optional<Orders> orders = orderRepo.findById(orderId);
        Orders obj = new Orders();
        int totalPrice;
        if (orders.isPresent()){
            try {
                totalPrice = obj.getQuantity() * obj.getProductPrice();
            } catch (Exception e) {
                throw new TotalPriceException("error calculating totalPrice!");
            }
        }else {
            throw new RuntimeException("Orders with the id, " + orderId + "not found");
        }
        return totalPrice;
    }
}
