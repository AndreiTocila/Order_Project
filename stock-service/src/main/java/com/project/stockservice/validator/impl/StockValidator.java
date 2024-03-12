package com.project.stockservice.validator.impl;

import com.project.order.dto.OrderKafkaDTO;
import com.project.stockservice.entity.Product;
import com.project.stockservice.exception.custom.InvalidFieldException;
import com.project.stockservice.repository.ProductRepository;
import com.project.stockservice.type.FieldType;
import com.project.stockservice.validator.FieldValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(FieldType.STOCK)
@Slf4j
@RequiredArgsConstructor
public class StockValidator implements FieldValidator
{
    private final ProductRepository productRepository;

    @Override
    public void validate(OrderKafkaDTO orderDTO)
    {
        log.info("Validating stock!");
        List<Product> products = productRepository.findAllById(orderDTO.getProductIds());
        List<Integer> productQuantities = orderDTO.getProductQuantities();
        for (int i = 0; i < products.size(); ++i)
        {
            Product product = products.get(i);
            if (product.getStock() == null || (product.getStock().getQuantity() < productQuantities.get(i)))
            {
                throw new InvalidFieldException("Not enough stock for product: id-" + product.getId() + " name-" + product.getName());
            }
        }
        log.info("Stock validated successfully!");
    }
}
