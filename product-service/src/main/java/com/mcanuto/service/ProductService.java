package com.mcanuto.service;

import com.mcanuto.model.Product;
import com.mcanuto.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String description, Integer quantity, BigDecimal price) {
        Product customer = new Product(description, quantity, price);
        return productRepository.save(customer);
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }
}