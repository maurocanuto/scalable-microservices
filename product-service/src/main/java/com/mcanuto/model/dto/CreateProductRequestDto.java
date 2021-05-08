package com.mcanuto.model.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreateProductRequestDto {
    String description;
    Integer quantity;
    BigDecimal price;

    public CreateProductRequestDto() {
    }

    public CreateProductRequestDto(String description, Integer quantity, BigDecimal price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price.setScale(2,  RoundingMode.HALF_UP);
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
