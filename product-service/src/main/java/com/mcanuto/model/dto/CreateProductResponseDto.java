package com.mcanuto.model.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreateProductResponseDto {
    private Long id;
    private String description;
    private Integer quantity;
    private BigDecimal price;

    public CreateProductResponseDto() {
    }

    public CreateProductResponseDto(Long id, String description, Integer quantity, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.price = price.setScale(2,  RoundingMode.HALF_UP);
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
