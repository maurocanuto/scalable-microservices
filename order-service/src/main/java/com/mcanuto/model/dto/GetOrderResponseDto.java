package com.mcanuto.model.dto;

import com.mcanuto.model.OrderState;
import com.mcanuto.model.RejectionReason;

public class GetOrderResponseDto {
    private Long orderId;
    private OrderState state;
    private RejectionReason rejectionReason;

    public GetOrderResponseDto() {
    }

    public GetOrderResponseDto(Long orderId, OrderState state, RejectionReason rejectionReason) {
        this.orderId = orderId;
        this.state = state;
        this.rejectionReason = rejectionReason;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderState getState() {
        return state;
    }

    public RejectionReason getRejectionReason() {
        return rejectionReason;
    }
}
