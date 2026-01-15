package com.example.Clothing_Management_System.service;

import com.example.Clothing_Management_System.repository.ProductRepository;
import com.example.Clothing_Management_System.entity.Product;
import com.example.Clothing_Management_System.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository repo;


    @Override
    public Product create(Product product) {
        return repo.save(product);
    }

    @Override
    public List<Product> getAll() {
        return repo.findAll();
    }

    @Override
    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
