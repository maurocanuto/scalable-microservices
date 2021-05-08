package com.mcanuto.service;

import com.mcanuto.model.Product;
import com.mcanuto.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct() {
        when(repository.save(any(Product.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        Product result = productService.createProduct("shoes", 10, new BigDecimal(79.99));
        assertEquals("shoes", result.getDescription());
        assertEquals(10, result.getQuantity());
        assertEquals(new BigDecimal(79.99), result.getPrice());
    }

    @Test
    void getProduct() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Product("shoes", 10, new BigDecimal(79.99))));
        Optional<Product> result = productService.getProduct(1L);
        assertEquals("shoes", result.get().getDescription());
        assertEquals(10, result.get().getQuantity());
        assertEquals(new BigDecimal(79.99), result.get().getPrice());
    }

    @Test
    void getProductNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Optional<Product> result = productService.getProduct(1L);
        assertTrue(result.isEmpty());
    }
}