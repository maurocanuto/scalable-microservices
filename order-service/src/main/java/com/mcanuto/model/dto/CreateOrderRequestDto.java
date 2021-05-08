package com.mcanuto.model.dto;

import java.math.BigDecimal;

public class CreateOrderRequestDto {
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private BigDecimal total;

    public CreateOrderRequestDto() {
    }

    public CreateOrderRequestDto(Long customerId, Long productId, Integer quantity, BigDecimal total) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
