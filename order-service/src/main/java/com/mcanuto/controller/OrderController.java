package com.mcanuto.controller;

import com.mcanuto.model.Order;
import com.mcanuto.model.OrderDetails;
import com.mcanuto.model.dto.CreateOrderRequestDto;
import com.mcanuto.model.dto.CreateOrderResponseDto;
import com.mcanuto.model.dto.GetOrderResponseDto;
import com.mcanuto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/orders")
public class OrderController {

    private final OrderService orderService;
    
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponseDto> createOrder(@Valid @RequestBody CreateOrderRequestDto request) {
        OrderDetails orderDetails = new OrderDetails(request.getCustomerId(), request.getProductId(), request.getQuantity(), request.getTotal());
        Order order = orderService.createOrder(orderDetails);
        return new ResponseEntity<>(
                new CreateOrderResponseDto(order.getId(), order.getState(), order.getRejectionReason()),
                HttpStatus.CREATED);
    }

    @GetMapping(path="/{orderId}")
    public ResponseEntity<GetOrderResponseDto> getOrder(@Valid @PathVariable long orderId) {
        return orderService.getOrder(orderId)
                .map(order -> new ResponseEntity<>(new GetOrderResponseDto(order.getId(), order.getState(), order.getRejectionReason()), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
