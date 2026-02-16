package com.clothing.Clothing_Management_System.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

