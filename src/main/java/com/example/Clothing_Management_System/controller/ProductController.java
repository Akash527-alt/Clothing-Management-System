package com.example.Clothing_Management_System.controller;


import com.example.Clothing_Management_System.entity.Product;
import com.example.Clothing_Management_System.entity.ProductType;
import com.example.Clothing_Management_System.service.ProductService;
import com.example.Clothing_Management_System.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductTypeService productTypeService;



    @PostMapping
    public Product create(@RequestBody Product product){
            if(product.getProductType() == null ||
            product.getProductType().getId() == null){
                throw new RuntimeException("productType.product_type_id is required");
            }

            ProductType productType  = productTypeService.getRequiredById(product.getProductType().getId());

            product.setProductType(productType);

            return productService.create(product);
    }


    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

}
