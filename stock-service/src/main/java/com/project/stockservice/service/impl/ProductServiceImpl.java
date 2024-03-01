package com.project.stockservice.service.impl;

import com.project.stockservice.dto.ProductDTO;
import com.project.stockservice.dto.ProductResponseDTO;
import com.project.stockservice.entity.Product;
import com.project.stockservice.mapper.ProductMapper;
import com.project.stockservice.repository.ProductRepository;
import com.project.stockservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductDTO productDTO)
    {
        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();

        productRepository.save(product);
    }

    @Override
    public List<ProductResponseDTO> getAll()
    {
        return productRepository
                .findAll()
                .stream()
                .map(ProductMapper::productToProductResponseDTO)
                .collect(Collectors.toList());
    }
}
