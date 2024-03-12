package com.project.feedbackservice.kafka;

import com.project.feedbackservice.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ValidationKafkaConsumer
{
    private final FeedbackService feedbackService;

    @KafkaListener(topics = "validation_feedback", groupId = "feedback_group", containerFactory = "containerFactory")
    public void consume(ConsumerRecord<String, String> record,
                        Acknowledgment ak)
    {
        log.info("Validating: " + record.value());
        feedbackService.processFeedback(record);
        ak.acknowledge();
    }
}
