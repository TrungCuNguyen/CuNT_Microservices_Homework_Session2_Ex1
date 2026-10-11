package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.OrderRequestDto;
import com.ecommerce.orderservice.dto.OrderResponseDto;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderStatus;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    @DisplayName("getHealthCheck should return 'Order Service is Up'")
    void getHealthCheck_ShouldReturnCorrectString() {
        String result = orderService.getHealthCheck();
        assertEquals("Order Service is Up", result);
    }

    @Test
    @DisplayName("createOrder should save and return OrderResponseDto")
    void createOrder_ShouldSaveAndReturnResponse() {
        OrderRequestDto request = OrderRequestDto.builder()
                .customerName("Nguyen Van A")
                .customerEmail("vana@example.com")
                .totalAmount(BigDecimal.valueOf(150.0))
                .status(OrderStatus.PENDING)
                .build();

        Order savedOrder = Order.builder()
                .id(1L)
                .orderNumber("ORD-TEST1234")
                .customerName("Nguyen Van A")
                .customerEmail("vana@example.com")
                .totalAmount(BigDecimal.valueOf(150.0))
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        OrderResponseDto response = orderService.createOrder(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("ORD-TEST1234", response.getOrderNumber());
        assertEquals("Nguyen Van A", response.getCustomerName());
        verify(orderRepository).save(any(Order.class));
    }
}
