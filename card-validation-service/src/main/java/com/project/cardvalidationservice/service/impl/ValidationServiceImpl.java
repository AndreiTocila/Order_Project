package com.project.cardvalidationservice.service.impl;

import com.project.cardvalidationservice.exception.custom.InvalidFieldException;
import com.project.cardvalidationservice.service.ValidationService;
import com.project.cardvalidationservice.validator.ValidatorFactory;
import com.project.order.dto.OrderKafkaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationServiceImpl implements ValidationService
{
    private final ValidatorFactory validatorFactory;

    private final KafkaTemplate<String,Object> kafkaTemplate;

    @Override
    public void validateOrder(ConsumerRecord<String, OrderKafkaDTO> record)
    {
        try
        {
            validatorFactory.validate(record.value());
            log.info("Sending passed feedback");
            kafkaTemplate.send("validation_feedback", record.key(), record.value().getId() + ":card:passed");
        }
        catch (InvalidFieldException ex)
        {
            log.info("Sending failed feedback");
            kafkaTemplate.send("validation_feedback", record.key(), record.value().getId() + ":card:failed");
            throw ex;
        }
    }
}
