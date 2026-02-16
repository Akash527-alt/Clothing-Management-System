package com.clothing.Clothing_Management_System.dto;

import com.clothing.Clothing_Management_System.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductRequest {

    private String name;
    private String brand;
    private String category;
    private Gender gender;
    private Double costPrice;
    private Double sellingPrice;
    private Integer quantity;
    private LocalDate addedDate;

    public ProductRequest(String name, String brand, String category, Gender gender, Double costPrice, Double sellingPrice, Integer quantity, LocalDate addedDate) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.gender = gender;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.addedDate = addedDate;
    }
}
