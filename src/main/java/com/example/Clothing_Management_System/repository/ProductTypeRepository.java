package com.example.Clothing_Management_System.repository;

import com.example.Clothing_Management_System.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {
}
