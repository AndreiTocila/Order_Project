package com.project.cardvalidationservice.validator.impl;

import com.project.cardvalidationservice.exception.custom.InvalidFieldException;
import com.project.cardvalidationservice.validator.FieldValidator;
import com.project.order.dto.OrderKafkaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class CardCvvValidator implements FieldValidator
{
    @Override
    public void validate(OrderKafkaDTO orderDTO)
    {
        log.info("Validating card CVV...");
        String cardCVV = orderDTO.getCardCVV();

        Pattern p = Pattern.compile("^[0-9]{3,4}$");

        Matcher m = p.matcher(cardCVV);

        if(!m.matches())
        {
            throw new InvalidFieldException("Card CVV is invalid!");
        }
        log.info("Validation finished!");
    }
}
