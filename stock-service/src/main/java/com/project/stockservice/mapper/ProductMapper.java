package com.project.stockservice.mapper;

import com.project.stockservice.dto.ProductResponseDTO;
import com.project.stockservice.entity.Product;
import com.project.stockservice.entity.Stock;

import java.util.Optional;

public class ProductMapper
{
    public static ProductResponseDTO productToProductResponseDTO(Product product)
    {
        Optional<Stock> stock = Optional.ofNullable(product.getStock());

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(stock.map(Stock::getQuantity).orElse(null))
                .build();
    }
}
