package com.project.feedbackservice.service.impl;

import com.project.feedbackservice.entity.ValidationStatus;
import com.project.feedbackservice.exception.custom.InvalidFeedbackException;
import com.project.feedbackservice.repository.ValidationStatusRepository;
import com.project.feedbackservice.service.FeedbackService;
import com.project.feedbackservice.type.ValidationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackServiceImpl implements FeedbackService
{
    private final ValidationStatusRepository validationStatusRepository;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void processFeedback(ConsumerRecord<String, String> record)
    {
        log.info("Processing feedback: " + record.value());

        String feedbackValue = record.value().replaceAll("\"", "");
        String[] feedbackInfo = feedbackValue.split(":");

        System.out.println(feedbackInfo[0]);
        System.out.println(feedbackInfo[1]);
        System.out.println(feedbackInfo[2]);

        if (feedbackInfo.length != 3)
        {
            throw new InvalidFeedbackException();
        }

        ValidationStatus validationStatus = validationStatusRepository.findByUserIdAndAndOrderId(record.key(), feedbackInfo[0]);

        if (validationStatus == null)
        {
            validationStatus = ValidationStatus.builder()
                    .userId(record.key())
                    .orderId(feedbackInfo[0])
                    .build();
        }

        switch (feedbackInfo[1])
        {
            case ValidationType.STOCK:
            {
                validationStatus.setStockValidation(feedbackInfo[2].equals("passed"));
                break;
            }
            case ValidationType.CARD:
            {
                validationStatus.setCardValidation(feedbackInfo[2].equals("passed"));
                break;
            }
            default:
            {
                throw new InvalidFeedbackException();
            }
        }

        validationStatus = validationStatusRepository.save(validationStatus);

        Boolean stockValidation = validationStatus.getStockValidation();
        Boolean cardValidation = validationStatus.getCardValidation();
        if (stockValidation != null && cardValidation != null)
        {
            if (stockValidation && cardValidation)
            {
                log.info("Sending success feedback!");
                kafkaTemplate.send("final_feedback", record.key(), feedbackInfo[0] + ":success");
            }
            else
            {
                log.info("Sending failed feedback!");
                kafkaTemplate.send("final_feedback", record.key(), feedbackInfo[0] + ":failed");
            }
        }
    }
}
