package com.project.cardvalidationservice.validator;

import com.project.order.dto.OrderKafkaDTO;

public interface FieldValidator
{
    void validate(OrderKafkaDTO orderDTO);
}
