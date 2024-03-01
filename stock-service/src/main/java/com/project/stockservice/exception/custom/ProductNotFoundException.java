package com.project.stockservice.exception.custom;

public class ProductNotFoundException extends RuntimeException
{
    public ProductNotFoundException()
    {
        super("Product not found!");
    }
}
