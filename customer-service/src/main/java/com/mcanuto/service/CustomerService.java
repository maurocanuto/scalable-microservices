package com.mcanuto.service;

import com.mcanuto.exceptions.EmailAlreadyUsedException;
import com.mcanuto.model.Customer;
import com.mcanuto.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(String email, String name) throws EmailAlreadyUsedException {
        BigDecimal initialCredit = new BigDecimal(0);
        Customer customer  = new Customer(email, name, initialCredit);
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e)   {
            throw new EmailAlreadyUsedException();
        }
    }

    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }
}