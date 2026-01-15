package com.example.Clothing_Management_System.repository;

import com.example.Clothing_Management_System.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}

