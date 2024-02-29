package com.project.orderservice.service;

import com.project.orderservice.dto.OrderDTO;
import org.springframework.security.oauth2.jwt.Jwt;

public interface OrderService
{
    void placeOrder(OrderDTO orderDTO);
}
