package com.clothing.Clothing_Management_System.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="product_type")
@Data
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="gender", length = 20, nullable = false)
    private Gender gender;


    public ProductType(){}

    public ProductType(Long id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }
    public ProductType(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }


}
