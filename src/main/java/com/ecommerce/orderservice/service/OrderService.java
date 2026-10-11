package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    String getHealthCheck();
    List<OrderResponseDto> getAllOrders();
    OrderResponseDto getOrderById(Long id);
    OrderResponseDto createOrder(OrderRequestDto requestDto);
    void deleteOrder(Long id);
}
