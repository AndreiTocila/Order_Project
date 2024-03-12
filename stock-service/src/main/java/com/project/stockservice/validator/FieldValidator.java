package com.project.stockservice.validator;

import com.project.order.dto.OrderKafkaDTO;

public interface FieldValidator
{
    void validate(OrderKafkaDTO orderDTO);
}
