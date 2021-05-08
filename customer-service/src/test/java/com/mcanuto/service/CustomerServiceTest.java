package com.mcanuto.service;

import com.mcanuto.exceptions.EmailAlreadyUsedException;
import com.mcanuto.model.Customer;
import com.mcanuto.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void createCustomer() throws EmailAlreadyUsedException {
        when(repository.save(any(Customer.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        Customer result = customerService.createCustomer("john@email.com","John");
        assertEquals("john@email.com", result.getEmail());
        assertEquals("John", result.getName());
        assertEquals(new BigDecimal(0), result.getCredit());
    }

    @Test
    void createCustomerWithEmailAlreadyUsed() {
        when(repository.save(any(Customer.class))).thenThrow(DataIntegrityViolationException.class);
        assertThrows(EmailAlreadyUsedException.class, () -> {
            customerService.createCustomer("john@email.com","John");
        });
    }

    @Test
    void getCustomer() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Customer("john@email.com","John", new BigDecimal(10.5))));
        Optional<Customer> result = customerService.getCustomer(1L);
        assertEquals("john@email.com", result.get().getEmail());
        assertEquals("John", result.get().getName());
        assertEquals(new BigDecimal(10.5), result.get().getCredit());
    }

    @Test
    void getCustomerNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Optional<Customer> result = customerService.getCustomer(1L);
        assertTrue(result.isEmpty());
    }
}