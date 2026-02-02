package com.clothing.Clothing_Management_System.controller;


import com.clothing.Clothing_Management_System.entity.Product;
import com.clothing.Clothing_Management_System.entity.ProductRequest;
import com.clothing.Clothing_Management_System.entity.ProductType;
import com.clothing.Clothing_Management_System.service.ProductService;
import com.clothing.Clothing_Management_System.service.ProductTypeService;
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
    public Product create(@RequestBody ProductRequest request){
            return productService.create(request);
    }


    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String q){
        return productService.search(q);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

}
