package com.clothing.Clothing_Management_System.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateSaleRequest {

    private List<Item> items;

    @Data
    public static class Item {
        private Long productId;
        private Integer quantity;
    }
}