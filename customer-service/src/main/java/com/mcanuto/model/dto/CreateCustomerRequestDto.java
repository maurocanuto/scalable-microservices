package com.mcanuto.model.dto;

public class CreateCustomerRequestDto {
    private String email;
    private String name;

    public CreateCustomerRequestDto() {
    }

    public CreateCustomerRequestDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
