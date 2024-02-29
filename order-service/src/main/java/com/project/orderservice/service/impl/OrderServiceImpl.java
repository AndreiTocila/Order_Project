package com.project.orderservice.service.impl;

import com.project.orderservice.dto.OrderDTO;
import com.project.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService
{
    private final KafkaTemplate<String,Object> kafkaTemplate;
    @Override
    public void placeOrder(OrderDTO orderDTO, Jwt jwt)
    {
        orderDTO.setId(UUID.randomUUID().toString());

        kafkaTemplate.send("order", jwt.getClaim("user_id"), orderDTO);

        System.out.println(orderDTO);
    }
}
