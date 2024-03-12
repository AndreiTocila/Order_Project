package com.project.cardvalidationservice.validator.impl;

import com.project.cardvalidationservice.exception.custom.InvalidFieldException;
import com.project.cardvalidationservice.validator.FieldValidator;
import com.project.order.dto.OrderKafkaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CardNumberValidator implements FieldValidator
{
    @Override
    public void validate(OrderKafkaDTO orderDTO)
    {
        log.info("Validating card number...");
        if(orderDTO.getCardNumber().length() != 16)
        {
            throw new InvalidFieldException("Card number has less/more than 16 digits!");
        }
        log.info("Validation finished!");
    }
}
