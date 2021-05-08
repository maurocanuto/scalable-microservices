package com.mcanuto.model;

import javax.persistence.*;

@Entity
@Table(name="order")
@Access(AccessType.FIELD)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Embedded
    private OrderDetails orderDetails;

    @Enumerated(EnumType.STRING)
    private RejectionReason rejectionReason;

    @Version
    private Long version;

    public Order() {
    }

    public Order(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
        this.state = OrderState.PENDING;
    }

    public Long getId() {
        return id;
    }

    public OrderState getState() {
        return state;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public RejectionReason getRejectionReason() {
        return rejectionReason;
    }
}
