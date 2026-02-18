package com.clothing.Clothing_Management_System.controller;

import com.clothing.Clothing_Management_System.dto.SaleDto;
import com.clothing.Clothing_Management_System.entity.Product;
import com.clothing.Clothing_Management_System.entity.Sale;
import com.clothing.Clothing_Management_System.projection.CategoryMonthlySummary;
import com.clothing.Clothing_Management_System.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/old-stock")
    public List<Product> oldStock(
            @RequestParam(defaultValue = "6") int months) {



        return reportService.getOldStock(months);
    }

    @GetMapping("/last-n-sales")
    public List<SaleDto> lastNSales(
            @RequestParam(defaultValue = "5") int n) {

        return reportService.getLastNSales(n);
    }


    @GetMapping("/last-month-category-summary")
    public List<CategoryMonthlySummary> getLastMonthSummary() {
        return reportService.getLastMonthCategorySummary();
    }

}

