package com.clothing.Clothing_Management_System.controller;
import com.clothing.Clothing_Management_System.entity.Product;
import com.clothing.Clothing_Management_System.entity.ProductRequest;
import com.clothing.Clothing_Management_System.entity.ProductType;
import com.clothing.Clothing_Management_System.service.ProductService;
import com.clothing.Clothing_Management_System.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ClothingShop")
public class ViewController {


    @GetMapping("/products")
    public String showProductsPage(){
        return "products/list";
    }

    @GetMapping("/billing_desk")
    public String billingDeskPage(){
        return "products/billing_desk";
    }


}

