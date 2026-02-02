package com.clothing.Clothing_Management_System.repository;

import com.clothing.Clothing_Management_System.entity.Gender;
import com.clothing.Clothing_Management_System.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {

    Optional<ProductType> findByNameAndGender(String name, Gender gender);
}
