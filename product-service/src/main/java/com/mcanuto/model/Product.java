package com.mcanuto.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="product")
@Access(AccessType.FIELD)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer quantity;
    private BigDecimal price;

    @Version
    private Long version;

    public Product() {
    }

    public Product(String description, Integer quantity, BigDecimal price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
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
