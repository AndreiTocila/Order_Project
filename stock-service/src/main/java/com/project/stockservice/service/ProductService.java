package com.project.stockservice.service;

import com.project.stockservice.dto.ProductDTO;
import com.project.stockservice.dto.ProductResponseDTO;
import com.project.stockservice.entity.Product;

import java.util.List;

public interface ProductService
{
    void createProduct(ProductDTO productDTO);

    List<ProductResponseDTO> getAll();
}
