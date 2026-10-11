package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Test
    @DisplayName("GET /api/v1/orders/health-check should return 'Order Service is Up'")
    void healthCheckShouldReturnExpectedMessage() throws Exception {
        when(orderService.getHealthCheck()).thenReturn("Order Service is Up");

        mockMvc.perform(get("/api/v1/orders/health-check"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order Service is Up"));
    }
}
