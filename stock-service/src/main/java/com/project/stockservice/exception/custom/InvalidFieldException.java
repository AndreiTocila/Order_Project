package com.project.stockservice.exception.custom;

public class InvalidFieldException extends RuntimeException
{
    public InvalidFieldException(String message)
    {
        super(message);
    }
}
