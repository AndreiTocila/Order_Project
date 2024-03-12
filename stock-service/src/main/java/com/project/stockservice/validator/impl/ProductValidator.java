package com.project.stockservice.validator.impl;

import com.project.order.dto.OrderKafkaDTO;
import com.project.stockservice.entity.Product;
import com.project.stockservice.exception.custom.InvalidFieldException;
import com.project.stockservice.repository.ProductRepository;
import com.project.stockservice.type.FieldType;
import com.project.stockservice.validator.FieldValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(FieldType.PRODUCT)
@Slf4j
@RequiredArgsConstructor
@Order(1)
public class ProductValidator implements FieldValidator
{
    private final ProductRepository productRepository;

    @Override
    public void validate(OrderKafkaDTO orderDTO)
    {
        log.info("Validating product!");
        List<Product> products = productRepository.findAllById(orderDTO.getProductIds());
        if (products.size() < orderDTO.getProductIds().size())
        {
            throw new InvalidFieldException("Invalid list of products. Not all products exist.");
        }
        log.info("Products validated successfully!");
    }
}