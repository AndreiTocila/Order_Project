package com.project.stockservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO
{
    String id;

    String cardNumber;

    @JsonFormat(pattern = "MM/YY")
    Date cardExpirationDate;

    String cardCVV;

    List<Integer> productIds;

    List<Long> productQuantities;
}
