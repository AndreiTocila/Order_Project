package com.project.stockservice.controller;

import com.project.stockservice.dto.ProductDTO;
import com.project.stockservice.dto.ProductResponseDTO;
import com.project.stockservice.entity.Product;
import com.project.stockservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController
{
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts()
    {
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO)
    {
        productService.createProduct(productDTO);
        return new ResponseEntity<>("Product added succesfully!", HttpStatus.CREATED);
    }
}
