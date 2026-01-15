package com.example.Clothing_Management_System.controller;


import com.example.Clothing_Management_System.entity.ProductType;
import com.example.Clothing_Management_System.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-types")
public class ProductTypeController {

    @Autowired
    private ProductTypeService service;

    @PostMapping
    public ResponseEntity<ProductType> create(@RequestBody ProductType productType){
        try{
            ProductType res = service.create(productType);
            return new ResponseEntity<ProductType>(res, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<ProductType>((ProductType) null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public List<ProductType> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductType getById(@PathVariable Long id) {
        return service.getById(id);
    }

}
