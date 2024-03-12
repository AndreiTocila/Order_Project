package com.project.cardvalidationservice.validator.impl;

import com.project.cardvalidationservice.exception.custom.InvalidFieldException;
import com.project.cardvalidationservice.validator.FieldValidator;
import com.project.order.dto.OrderKafkaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class CardExpirationDateValidator implements FieldValidator
{
    @Override
    public void validate(OrderKafkaDTO orderDTO)
    {
        log.info("Validating card expiration date...");
        LocalDate expirationDate = orderDTO.getCardExpirationDate();

        if (expirationDate.isBefore(LocalDate.now()))
        {
            throw new InvalidFieldException("Card expired");
        }
        log.info("Validation finished!");
    }
}
