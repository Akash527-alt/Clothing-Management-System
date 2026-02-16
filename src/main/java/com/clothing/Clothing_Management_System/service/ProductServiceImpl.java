package com.clothing.Clothing_Management_System.service;

import com.clothing.Clothing_Management_System.entity.ProductRequest;
import com.clothing.Clothing_Management_System.repository.ProductRepository;
import com.clothing.Clothing_Management_System.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepo;



    @Override
    public Product create(ProductRequest req) {

        Product product = new Product();
        product.setName(req.getName());
        product.setBrand(req.getBrand());
        product.setGender(req.getGender());
        product.setCategory(req.getCategory());
        product.setCostPrice(req.getCostPrice());
        product.setSellingPrice(req.getSellingPrice());
        product.setQuantity(req.getQuantity());
        product.setAddedDate(req.getAddedDate());

        return productRepo.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<Product> search(String q) {
        return productRepo.search(q);
    }


}
