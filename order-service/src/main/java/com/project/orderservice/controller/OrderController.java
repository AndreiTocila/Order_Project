package com.project.orderservice.controller;

import com.project.orderservice.dto.OrderDTO;
import com.project.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController
{
    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<String> placeOrder(@RequestBody OrderDTO orderDTO, @AuthenticationPrincipal Jwt jwt)
    {
        orderService.placeOrder(orderDTO, jwt);
        return new ResponseEntity<>("Order placed!", HttpStatus.CREATED);
    }
}
