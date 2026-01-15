package com.example.Clothing_Management_System.service;

import com.example.Clothing_Management_System.repository.ProductTypeRepository;
import com.example.Clothing_Management_System.entity.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductTypeServiceImpl implements ProductTypeService{

    @Autowired
    ProductTypeRepository productTypeRepo;


    @Override
    public ProductType create(ProductType productType) {
        return productTypeRepo.save(productType);
    }

    @Override
    public List<ProductType> getAll() {
        return productTypeRepo.findAll();
    }

    @Override
    public ProductType getById(Long id) {
        return productTypeRepo.findById(id).orElse(null);
    }

    @Override
    public ProductType getRequiredById(Long id) {
        return productTypeRepo.findById(id).orElseThrow(()-> new RuntimeException("product not found with id: "+id));
    }
}
