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

    @GetMapping("/old_stock_report")
    public String old_stock_report(){
        return "products/old_stock_report";
    }

    @GetMapping("/last-n-sales")
    public String lastNSalesPage(){
        return "products/last-n-sales";
    }

    @GetMapping("/category-wise-sales-summary")
    public String lastMonthSalesCategoryWise(){
        return "products/category-wise-sales-summary";
    }


}

