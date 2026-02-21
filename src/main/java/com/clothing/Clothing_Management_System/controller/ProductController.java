package com.clothing.Clothing_Management_System.controller;


import com.clothing.Clothing_Management_System.dto.UpdateProductRequest;
import com.clothing.Clothing_Management_System.entity.Product;
import com.clothing.Clothing_Management_System.dto.ProductRequest;
import com.clothing.Clothing_Management_System.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;




    @PostMapping
    public Product create(@RequestBody ProductRequest request){
            return productService.create(request);
    }


    @GetMapping("/getAll")
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

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest request) {

        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok().build();
    }

}
