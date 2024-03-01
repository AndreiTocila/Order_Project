package com.project.stockservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductDTO
{
    @Size(min = 3)
    private String name;

    @NotNull
    private Double price;
}
