package com.project.stockservice.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class ProductResponseDTO
{
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;
}
