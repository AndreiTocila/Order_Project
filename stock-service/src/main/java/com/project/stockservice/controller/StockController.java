package com.project.stockservice.controller;

import com.project.stockservice.dto.StockDTO;
import com.project.stockservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/{id}/stock")
public class StockController
{
    private final StockService stockService;

    @PostMapping()
    public ResponseEntity<String> addStock(@PathVariable Long id, @RequestBody StockDTO stockDTO)
    {
        stockService.addStock(id, stockDTO);
        return new ResponseEntity<>("Stock added succesfully.", HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestBody StockDTO stockDTO)
    {
        stockService.updateStock(id, stockDTO);
        return new ResponseEntity<>("Stock updated succesfully.", HttpStatus.OK);
    }
}
