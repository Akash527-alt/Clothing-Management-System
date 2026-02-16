package com.clothing.Clothing_Management_System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(name = "added_date", nullable = false)
    private LocalDate addedDate;

    // Pricing
    @Column(nullable = false)
    private Double costPrice;

    @Column(nullable = false)
    private Double sellingPrice;

    // Inventory
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;


    public Product() {
    }


    public Product(String name, String brand, Double costPrice, Double sellingPrice, Integer quantity, String category,Gender gender,LocalDate addedDate ) {
        this.name = name;
        this.brand = brand;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.category = category;
        this.gender = gender;
        this.addedDate = addedDate;
    }



}

