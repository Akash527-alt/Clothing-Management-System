package com.example.Clothing_Management_System.entity;

import jakarta.persistence.*;

@Entity
@Table(name="product_type")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
