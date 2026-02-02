package com.clothing.Clothing_Management_System.service;

import com.clothing.Clothing_Management_System.entity.ProductType;

import java.util.List;

public interface ProductTypeService {
    ProductType create(ProductType productType);
    List<ProductType> getAll();
    ProductType getById(Long id);
    ProductType getRequiredById(Long id);

}
