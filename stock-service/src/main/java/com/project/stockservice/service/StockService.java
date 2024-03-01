package com.project.stockservice.service;

import com.project.stockservice.dto.StockDTO;

public interface StockService
{
    void addStock(Long productId, StockDTO stockDTO);

    void updateStock(Long productId, StockDTO stockDTO);
}
