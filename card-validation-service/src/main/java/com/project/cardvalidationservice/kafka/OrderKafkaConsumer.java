package com.project.cardvalidationservice.kafka;

import com.project.cardvalidationservice.service.ValidationService;
import com.project.order.dto.OrderKafkaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderKafkaConsumer
{
    private final ValidationService validationService;

    @KafkaListener(topics = "order", groupId = "card_group", containerFactory = "containerFactory")
    public void consume(ConsumerRecord<String, OrderKafkaDTO> record,
                        Acknowledgment ak)
    {
        log.info("Validating: " + record.value());
        validationService.validateOrder(record);
        ak.acknowledge();
    }
}
