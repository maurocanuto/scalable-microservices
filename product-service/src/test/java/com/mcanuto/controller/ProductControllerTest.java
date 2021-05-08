package com.mcanuto.controller;

import com.mcanuto.model.Product;
import com.mcanuto.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void createProduct() throws Exception {
        when(productService.createProduct(anyString(), anyInt(), any(BigDecimal.class))).thenReturn(new Product("shoes", 10, new BigDecimal(79.99)));
        this.mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content("{\"description\":\"shoes\",\"quantity\":10, \"price\": 79.99}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("shoes"))
                .andExpect(jsonPath("$.quantity").value("10"))
                .andExpect(jsonPath("$.price").value("79.99"));
    }

    @Test
    void createProductRequestWithoutBody() throws Exception {
        this.mockMvc.perform(post("/products"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void getProduct() throws Exception {
        when(productService.getProduct(1L)).thenReturn(Optional.of(new Product("shoes", 10, new BigDecimal(79.99))));
        this.mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("shoes"))
                .andExpect(jsonPath("$.quantity").value("10"))
                .andExpect(jsonPath("$.price").value("79.99"));
    }

    @Test
    void getProductNotFound() throws Exception {
        when(productService.getProduct(1L)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/products/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }
}