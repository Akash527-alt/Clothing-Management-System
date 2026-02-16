package com.clothing.Clothing_Management_System.service;

import com.clothing.Clothing_Management_System.dto.CreateSaleRequest;
import com.clothing.Clothing_Management_System.dto.SaleResponse;
import com.clothing.Clothing_Management_System.entity.Product;
import com.clothing.Clothing_Management_System.entity.Sale;
import com.clothing.Clothing_Management_System.entity.SaleItem;
import com.clothing.Clothing_Management_System.repository.ProductRepository;
import com.clothing.Clothing_Management_System.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SaleService {

    private final SaleRepository saleRepo;
    private final ProductRepository productRepo;

    public SaleResponse createSale(CreateSaleRequest request) {

        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sale must contain at least one item"
            );
        }

        Sale sale = new Sale();
        sale.setSaleDate(LocalDate.now());

        double totalAmount = 0;

        for (CreateSaleRequest.Item reqItem : request.getItems()) {

            // 1️⃣ Fetch product
            Product product = productRepo.findById(reqItem.getProductId())
                    .orElseThrow(() ->
                            new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Product not found with id: " + reqItem.getProductId()
                            )
                    );

            // 2️⃣ Check stock
            if (product.getQuantity() < reqItem.getQuantity()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Not enough stock for product: " + product.getName()
                );
            }

            // 3️⃣ Deduct stock
            product.setQuantity(product.getQuantity() - reqItem.getQuantity());

            // 4️⃣ Create SaleItem
            SaleItem item = new SaleItem();
            item.setProduct(product);
            item.setQuantity(reqItem.getQuantity());
            item.setUnitPrice(product.getSellingPrice());

            double lineTotal = product.getSellingPrice() * reqItem.getQuantity();
            item.setLineTotal(lineTotal);

            // 5️⃣ Connect both sides
            item.setSale(sale);
            sale.getItems().add(item);

            totalAmount += lineTotal;
        }

        // ✅ VERY IMPORTANT FIX
        sale.setTotalAmount(totalAmount);

        Sale savedSale = saleRepo.save(sale);

        return mapToResponse(savedSale);
    }


    private SaleResponse mapToResponse(Sale sale) {

        SaleResponse response = new SaleResponse();
        response.setId(sale.getId());
        response.setSaleDate(sale.getSaleDate());
        response.setTotalAmount(sale.getTotalAmount());

        List<SaleResponse.Item> items = sale.getItems().stream().map(item -> {
            SaleResponse.Item dto = new SaleResponse.Item();
            dto.setProductName(item.getProduct().getName());
            dto.setQuantity(item.getQuantity());
            dto.setUnitPrice(item.getUnitPrice());
            dto.setLineTotal(item.getLineTotal());
            return dto;
        }).toList();

        response.setItems(items);

        return response;
    }
}