package com.mcanuto.model;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class OrderDetails {
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private BigDecimal orderTotal;

    public OrderDetails() {
    }

    public OrderDetails(Long customerId, Long productId, Integer quantity, BigDecimal orderTotal) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderTotal = orderTotal;
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

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

}
