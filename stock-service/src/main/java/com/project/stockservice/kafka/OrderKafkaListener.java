package com.project.stockservice.kafka;

import com.project.order.dto.OrderKafkaDTO;
import com.project.stockservice.service.FeedbackService;
import com.project.stockservice.service.StockService;
import com.project.stockservice.service.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderKafkaListener
{
    private final ValidationService validationService;

    private final FeedbackService feedbackService;

    @KafkaListener(topics = "order", groupId = "stock_group", containerFactory = "containerFactory")
    public void consume(ConsumerRecord<String, OrderKafkaDTO> record,
                        Acknowledgment ak)
    {
        log.info("Validating: " + record.value());
        validationService.validateOrder(record);
        ak.acknowledge();
    }

    @KafkaListener(topics = "final_feedback", groupId = "stock_group", containerFactory = "containerFactoryString")
    public void consumeFeedback(ConsumerRecord<String, String> record,
                        Acknowledgment ak)
    {
        log.info("Receiving: " + record.value());
        feedbackService.processFeedback(record.value());
        ak.acknowledge();
    }
}
