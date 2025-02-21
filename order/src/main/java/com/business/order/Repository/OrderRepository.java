package com.business.order.Repository;

import com.business.order.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
