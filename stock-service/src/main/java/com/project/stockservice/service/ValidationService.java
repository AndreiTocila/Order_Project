package com.project.stockservice.service;

import com.project.order.dto.OrderKafkaDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ValidationService
{
    void validateOrder(ConsumerRecord<String, OrderKafkaDTO> record);
}
