package com.clothing.Clothing_Management_System.projection;

public interface CategoryMonthlySummary {

    String getCategory();
    Long getTotalQuantity();
    Double getTotalSales();
    Double getTotalProfit();
}

