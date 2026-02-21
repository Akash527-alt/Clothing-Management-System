package com.clothing.Clothing_Management_System.service;

import com.clothing.Clothing_Management_System.dto.ProductRequest;
import com.clothing.Clothing_Management_System.dto.UpdateProductRequest;
import com.clothing.Clothing_Management_System.repository.ProductRepository;
import com.clothing.Clothing_Management_System.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService{



    @Autowired
    ProductRepository productRepo;



    @Override
    public Product create(ProductRequest req) {

        Product product = new Product();
        product.setName(req.getName());
        product.setBrand(req.getBrand());
        product.setGender(req.getGender());
        product.setCategory(req.getCategory());
        product.setCostPrice(req.getCostPrice());
        product.setSellingPrice(req.getSellingPrice());
        product.setQuantity(req.getQuantity());
        product.setAddedDate(req.getAddedDate());

        return productRepo.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, UpdateProductRequest request) {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }

        if (request.getCostPrice() != null) {
            product.setCostPrice(request.getCostPrice());
        }

        if (request.getSellingPrice() != null) {
            product.setSellingPrice(request.getSellingPrice());
        }

        product.setAddedDate(LocalDate.now());

        return productRepo.save(product);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = productRepo.findAll();

        products.sort(
                Comparator
                        .comparing((Product p) -> p.getQuantity() == 0)
                        .thenComparing(Product::getQuantity)
        );

        return products;
    }

    @Override
    public Product getById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<Product> search(String q) {
        return productRepo.search(q);
    }

    @Override
    public void deleteProduct(Long id) {

        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        productRepo.deleteById(id);
    }

}
