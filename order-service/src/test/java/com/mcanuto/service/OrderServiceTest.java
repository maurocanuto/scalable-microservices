package com.mcanuto.service;

import com.mcanuto.model.Order;
import com.mcanuto.model.OrderDetails;
import com.mcanuto.model.OrderState;
import com.mcanuto.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder() {
        when(repository.save(any(Order.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        Order result = orderService.createOrder(new OrderDetails(1L, 2L, 10, new BigDecimal(89.99)));
        assertEquals(1L, result.getOrderDetails().getCustomerId());
        assertEquals(2L, result.getOrderDetails().getProductId());
        assertEquals(10, result.getOrderDetails().getQuantity());
        assertEquals(new BigDecimal(89.99), result.getOrderDetails().getOrderTotal());
        assertEquals(OrderState.PENDING, result.getState());
    }

    @Test
    void getOrder() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Order(new OrderDetails(1L, 2L, 10, new BigDecimal(89.99)))));
        Optional<Order> result = orderService.getOrder(1L);
        assertEquals(1L, result.get().getOrderDetails().getCustomerId());
        assertEquals(2L, result.get().getOrderDetails().getProductId());
        assertEquals(10, result.get().getOrderDetails().getQuantity());
        assertEquals(new BigDecimal(89.99), result.get().getOrderDetails().getOrderTotal());
        assertEquals(OrderState.PENDING, result.get().getState());
    }

    @Test
    void getOrderNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Optional<Order> result = orderService.getOrder(1L);
        assertTrue(result.isEmpty());
    }
}