package com.clothing.Clothing_Management_System.service;


import com.clothing.Clothing_Management_System.dto.SaleDto;
import com.clothing.Clothing_Management_System.entity.Product;
import com.clothing.Clothing_Management_System.projection.CategoryMonthlySummary;
import com.clothing.Clothing_Management_System.repository.ProductRepository;
import com.clothing.Clothing_Management_System.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {

    private final ProductRepository productRepo;
    private final SaleRepository saleRepo;

    public List<Product> getOldStock(int months) {

        LocalDate cutoffDate = LocalDate.now().minusMonths(months);

        return productRepo.findOldStock(cutoffDate);
    }


    public List<SaleDto> getLastNSales(int n) {

        return saleRepo
                .findAllByOrderBySaleDateDesc(PageRequest.of(0, n))
                .stream()
                .map(s -> new SaleDto(
                        s.getId(),
                        s.getSaleDate(),
                        s.getTotalAmount()
                ))
                .toList();
    }

    public List<CategoryMonthlySummary> getLast30DaysCategorySummary() {

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);

        return saleRepo
                .getLastMonthCategorySummary(startDate, endDate);
    }

}
