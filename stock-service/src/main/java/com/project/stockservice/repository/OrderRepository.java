package com.project.stockservice.repository;

import com.project.stockservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>
{
    Optional<Order> findByOrderId (String orderId);
}
