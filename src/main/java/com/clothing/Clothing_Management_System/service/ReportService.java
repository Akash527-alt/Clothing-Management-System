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
                .findAllByOrderByIdDesc(PageRequest.of(0, n))
                .stream()
                .map(s -> {

                    double totalProfit = s.getItems()
                            .stream()
                            .mapToDouble(item ->
                                    (item.getUnitPrice()
                                            - item.getProduct().getCostPrice())
                                            * item.getQuantity()
                            )
                            .sum();

                    return new SaleDto(
                            s.getId(),
                            s.getSaleDate(),
                            s.getTotalAmount(),
                            totalProfit
                    );
                })
                .toList();
    }

    public List<CategoryMonthlySummary> getCategorySummaryByDateRange(
            LocalDate startDate,
            LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        return saleRepo.getCategorySummaryByDateRange(startDate, endDate);
    }

}
