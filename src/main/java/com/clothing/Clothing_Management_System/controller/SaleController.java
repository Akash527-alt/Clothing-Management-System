package com.clothing.Clothing_Management_System.controller;

import com.clothing.Clothing_Management_System.dto.CreateSaleRequest;
import com.clothing.Clothing_Management_System.dto.SaleResponse;
import com.clothing.Clothing_Management_System.entity.Sale;
import com.clothing.Clothing_Management_System.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public SaleResponse create(@RequestBody CreateSaleRequest request) {
        return saleService.createSale(request);
    }
}