package com.project.stockservice.service;

import com.project.order.dto.OrderKafkaDTO;
import com.project.stockservice.dto.OrderDTO;

public interface OrderService
{
    void saveTempOrder(OrderKafkaDTO orderKafkaDTO);
}
