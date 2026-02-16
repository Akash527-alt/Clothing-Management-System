package com.clothing.Clothing_Management_System.service;


import com.clothing.Clothing_Management_System.entity.Product;
import com.clothing.Clothing_Management_System.entity.ProductRequest;

import java.util.List;

public interface ProductService {
    Product create(ProductRequest req);
    List<Product> getAll();
    Product getById(Long id);
    List<Product> search(String q);


}
