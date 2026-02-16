package com.clothing.Clothing_Management_System.dto;


import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class SaleResponse {

    private Long id;
    private LocalDate saleDate;
    private double totalAmount;
    private List<Item> items;

    @Data
    public static class Item {
        private String productName;
        private int quantity;
        private double unitPrice;
        private double lineTotal;
    }
}
