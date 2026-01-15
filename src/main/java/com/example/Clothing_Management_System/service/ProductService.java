package com.example.Clothing_Management_System.service;


import com.example.Clothing_Management_System.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    List<Product> getAll();
    Product getById(Long id);
}
