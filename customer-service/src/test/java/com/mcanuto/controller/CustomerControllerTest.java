package com.mcanuto.controller;

import com.mcanuto.exceptions.EmailAlreadyUsedException;
import com.mcanuto.model.Customer;
import com.mcanuto.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void createCustomer() throws Exception {
        when(customerService.createCustomer("john@email.com", "John")).thenReturn(new Customer("john@email.com", "John", new BigDecimal(0)));
        this.mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"john@email.com\",\"name\":\"John\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("john@email.com"))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.credit").value("0"));
    }

    @Test
    void createCustomerRequestWithoutBody() throws Exception {
        when(customerService.createCustomer("john@email.com","John")).thenReturn(new Customer("john@email.com", "John", new BigDecimal(0)));
        this.mockMvc.perform(post("/customers"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void createCustomerWithEmailAlreadyUsed() throws Exception {
        when(customerService.createCustomer("john@email.com","John")).thenThrow(EmailAlreadyUsedException.class);
        this.mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"john@email.com\",\"name\":\"John\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void getCustomer() throws Exception {
        when(customerService.getCustomer(1L)).thenReturn(Optional.of(new Customer("john@email.com", "John", new BigDecimal(10.5))));
        this.mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@email.com"))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.credit").value("10.5"));
    }

    @Test
    void getCustomerNotFound() throws Exception {
        when(customerService.getCustomer(1L)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/customers/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }
}