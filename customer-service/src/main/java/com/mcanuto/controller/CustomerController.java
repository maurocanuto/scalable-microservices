package com.mcanuto.controller;

import com.mcanuto.exceptions.EmailAlreadyUsedException;
import com.mcanuto.model.Customer;
import com.mcanuto.service.CustomerService;
import com.mcanuto.model.dto.CreateCustomerRequestDto;
import com.mcanuto.model.dto.CreateCustomerResponseDto;
import com.mcanuto.model.dto.GetCustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CreateCustomerResponseDto> createCustomer(@Valid @RequestBody CreateCustomerRequestDto request) throws EmailAlreadyUsedException {
        Customer customer = customerService.createCustomer(request.getEmail(), request.getName());
        return new ResponseEntity<>(
                new CreateCustomerResponseDto(customer.getId(), customer.getEmail(), customer.getName(), customer.getCredit()),
                HttpStatus.CREATED);
    }

    @GetMapping(path="/{customerId}")
    public ResponseEntity<GetCustomerResponseDto> getCustomer(@Valid @PathVariable long customerId) {
        return customerService.getCustomer(customerId)
                .map(customer -> new ResponseEntity<>(new GetCustomerResponseDto(customer.getId(), customer.getEmail(), customer.getName(), customer.getCredit()), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
