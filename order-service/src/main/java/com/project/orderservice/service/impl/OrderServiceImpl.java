package com.project.orderservice.service.impl;

import com.project.order.dto.OrderKafkaDTO;
import com.project.orderservice.dto.OrderDTO;
import com.project.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService
{
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void placeOrder(OrderDTO orderDTO, Jwt jwt)
    {
        orderDTO.setId(UUID.randomUUID().toString());

        OrderKafkaDTO orderKafkaDTO = OrderKafkaDTO.newBuilder()
                .setId(orderDTO.getId())
                .setCardNumber(orderDTO.getCardNumber())
                .setCardExpirationDate(LocalDate.ofInstant(orderDTO.getCardExpirationDate().toInstant(), ZoneId.systemDefault()))
                .setCardCVV(orderDTO.getCardCVV())
                .setProductIds(orderDTO.getProductIds())
                .setProductQuantities(orderDTO.getProductQuantities())
                .build();

        kafkaTemplate.send("order", jwt.getClaim("user_id"), orderKafkaDTO);

        System.out.println(orderDTO);
    }
}
