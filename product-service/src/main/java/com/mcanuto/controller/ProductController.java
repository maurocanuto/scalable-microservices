package com.mcanuto.controller;

import com.mcanuto.model.Product;
import com.mcanuto.model.dto.CreateProductRequestDto;
import com.mcanuto.model.dto.CreateProductResponseDto;
import com.mcanuto.model.dto.GetProductResponseDto;
import com.mcanuto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<CreateProductResponseDto> createProduct(@Valid @RequestBody CreateProductRequestDto request) {
        Product product = productService.createProduct(request.getDescription(), request.getQuantity(), request.getPrice());
        return new ResponseEntity<>(
                new CreateProductResponseDto(product.getId(), request.getDescription(), request.getQuantity(), request.getPrice()),
                HttpStatus.CREATED);
    }

    @GetMapping(path="/{productId}")
    public ResponseEntity<GetProductResponseDto> getProduct(@Valid @PathVariable long productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(new GetProductResponseDto(product.getId(), product.getDescription(), product.getQuantity(), product.getPrice()), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
