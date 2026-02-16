package com.clothing.Clothing_Management_System.dto;

import lombok.Data;

@Data
public class UpdateProductRequest {

    private Integer quantity;
    private Double costPrice;
    private Double sellingPrice;
}
