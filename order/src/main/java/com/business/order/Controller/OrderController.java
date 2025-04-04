package com.business.order.Controller;

import com.business.order.Entity.Orders;
import com.business.order.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/new")
    public ResponseEntity<Orders> saveOrders (@RequestBody Orders orders) {
        Orders newOrders =orderService.saveOrders(orders);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrders);
    }

    @GetMapping("/Orders/{orderId}")
    public ResponseEntity<Orders> getOrdersById (@PathVariable (value = "orderId") int orderId) {
        Orders getOrders = orderService.getOrdersById(orderId);
        orderService.getTotalPrice(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(getOrders);
    }
}
