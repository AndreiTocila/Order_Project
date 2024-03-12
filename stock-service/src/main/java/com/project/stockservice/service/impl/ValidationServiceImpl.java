package com.project.stockservice.service.impl;

import com.project.order.dto.OrderKafkaDTO;
import com.project.stockservice.exception.custom.InvalidFieldException;
import com.project.stockservice.service.OrderService;
import com.project.stockservice.service.ValidationService;
import com.project.stockservice.validator.ValidatorFactory;
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

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final OrderService orderService;

    @Override
    public void validateOrder(ConsumerRecord<String, OrderKafkaDTO> record)
    {
        try
        {
            validatorFactory.validate(record.value());
            log.info("Saving temp order!");
            orderService.saveTempOrder(record.value());
            log.info("Sending passed feedback");
            kafkaTemplate.send("validation_feedback", record.key(), record.value().getId() + ":stock:passed");
        }
        catch (InvalidFieldException ex)
        {
            log.info("Sending failed feedback");
            kafkaTemplate.send("validation_feedback", record.key(), record.value().getId() + ":stock:failed");
            throw ex;
        }

    }
}
