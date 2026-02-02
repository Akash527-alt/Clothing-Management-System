package com.clothing.Clothing_Management_System.entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String brand;
    private double costPrice;
    private double sellingPrice;
    private int quantity;
    private String description;

    private String productTypeName;
    private Gender gender;

    public ProductRequest(){}

    public ProductRequest(String name, String brand, double costPrice, double sellingPrice, int quantity, String description, String productTypeName, Gender gender) {
        this.name = name;
        this.brand = brand;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.description = description;
        this.productTypeName = productTypeName;
        this.gender = gender;
    }
}
