package com.project.stockservice.exception.custom;

public class StockNotFoundException extends RuntimeException
{
    public StockNotFoundException()
    {
        super("Stock not found!");
    }
}
