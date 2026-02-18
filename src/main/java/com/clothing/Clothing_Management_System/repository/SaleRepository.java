package com.clothing.Clothing_Management_System.repository;

import com.clothing.Clothing_Management_System.entity.Sale;
import com.clothing.Clothing_Management_System.projection.CategoryMonthlySummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    List<Sale> findAllByOrderBySaleDateDesc(Pageable pageable);


    @Query("""
            SELECT 
                p.category AS category,
                SUM(si.quantity) AS totalQuantity,
                SUM(si.lineTotal) AS totalSales,
                SUM((si.unitPrice - p.costPrice) * si.quantity) AS totalProfit
            FROM SaleItem si
            JOIN si.product p
            JOIN si.sale s
            WHERE s.saleDate BETWEEN :startDate AND :endDate
            GROUP BY p.category
            ORDER BY SUM(si.lineTotal) DESC
""")
    List<CategoryMonthlySummary> getLastMonthCategorySummary(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );



}
