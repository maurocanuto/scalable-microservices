package com.mcanuto.model.dto;

import java.math.BigDecimal;

public class CreateCustomerResponseDto {
    private Long id;
    private String email;
    private String name;
    private BigDecimal credit;

    public CreateCustomerResponseDto() {
    }

    public CreateCustomerResponseDto(Long id, String email, String name, BigDecimal credit) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCredit() {
        return credit;
    }
}
