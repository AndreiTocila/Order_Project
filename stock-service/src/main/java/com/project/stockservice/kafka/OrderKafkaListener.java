package com.project.stockservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaListener
{
    @KafkaListener(topics = "order", groupId = "stock_group", containerFactory = "containerFactory")
    public void consume (@Payload String message,
                         @Header(KafkaHeaders.RECEIVED_KEY) String key,
                         Acknowledgment ak)
    {
        System.out.println(key);
        System.out.println(message);
        ak.acknowledge();
    }
}
