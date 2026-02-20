package com.clothing.Clothing_Management_System.dto;

import java.time.LocalDate;

public record SaleDto(
        Long id,
        LocalDate saleDate,
        Double totalAmount,
        Double totalProfit
) {}
