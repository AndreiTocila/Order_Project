package com.project.stockservice.service.impl;

import com.project.stockservice.dto.StockDTO;
import com.project.stockservice.entity.Product;
import com.project.stockservice.entity.Stock;
import com.project.stockservice.exception.custom.ProductNotFoundException;
import com.project.stockservice.exception.custom.StockNotFoundException;
import com.project.stockservice.repository.ProductRepository;
import com.project.stockservice.repository.StockRepository;
import com.project.stockservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService
{
    private final StockRepository stockRepository;

    private final ProductRepository productRepository;

    @Override
    public void addStock(Long productId, StockDTO stockDTO)
    {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        Stock stock = Stock.builder()
                .quantity(stockDTO.getQuantity())
                .product(product)
                .build();

        stockRepository.save(stock);
    }

    @Override
    public void updateStock(Long productId, StockDTO stockDTO)
    {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        Stock stock = Optional.ofNullable(product.getStock()).orElseThrow(StockNotFoundException::new);

        stock.setQuantity(stockDTO.getQuantity());

        stockRepository.save(stock);
    }
}
