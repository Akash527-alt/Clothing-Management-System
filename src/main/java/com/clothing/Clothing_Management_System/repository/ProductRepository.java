package com.clothing.Clothing_Management_System.repository;

import com.clothing.Clothing_Management_System.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("""
        SELECT p FROM Product p
        WHERE
            (:q IS NULL OR :q = '' OR
             CAST(p.id AS string) LIKE CONCAT('%', :q, '%') OR
             LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%')) OR
             LOWER(p.brand) LIKE LOWER(CONCAT('%', :q, '%')) OR
             LOWER(p.category) LIKE LOWER(CONCAT('%', :q, '%'))
            )
        """)
    List<Product> search(@Param("q") String q);



    @Query("""
        SELECT p FROM Product p
        WHERE p.addedDate <= :cutoffDate
        ORDER BY p.addedDate ASC
        """)
    List<Product> findOldStock(@Param("cutoffDate") LocalDate cutoffDate);




//    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

}

