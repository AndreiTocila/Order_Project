package com.project.stockservice.validator;

import com.project.order.dto.OrderKafkaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ValidatorFactory
{
    private final Map<String, FieldValidator> fieldValidatorMap;

    public FieldValidator getFieldValidator(String validatorName)
    {
        FieldValidator fieldValidator = fieldValidatorMap.get(validatorName);
        if (fieldValidator == null)
        {
            throw new RuntimeException("Unsupported validator");
        }
        return fieldValidator;
    }

    public void validate(OrderKafkaDTO orderDTO)
    {
        for (FieldValidator fieldValidator : fieldValidatorMap.values())
        {
            fieldValidator.validate(orderDTO);
        }
    }
}
