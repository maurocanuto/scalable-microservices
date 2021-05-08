package com.mcanuto.controller;

import com.mcanuto.model.Order;
import com.mcanuto.model.OrderDetails;
import com.mcanuto.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void createOrder() throws Exception {
        when(orderService.createOrder(any(OrderDetails.class))).thenReturn(new Order(new OrderDetails(1L, 2L, 10, new BigDecimal(89.99))));
        this.mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).content("{\"customerId\":1,\"productId\":2,\"quantity\":10,\"total\": 89.99}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.state").value("PENDING"))
                .andExpect(jsonPath("$.rejectedReason").doesNotExist());
    }

    @Test
    void createOrderRequestWithoutBody() throws Exception {
        this.mockMvc.perform(post("/orders"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void getOrder() throws Exception {
        when(orderService.getOrder(1L)).thenReturn(Optional.of(new Order(new OrderDetails(1L, 2L, 10, new BigDecimal(89.99)))));
        this.mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("PENDING"))
                .andExpect(jsonPath("$.rejectedReason").doesNotExist());
    }

    @Test
    void getOrderNotFound() throws Exception {
        when(orderService.getOrder(1L)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/orders/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }
}