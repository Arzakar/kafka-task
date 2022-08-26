package com.rntgroup.client.app.controller;

import com.rntgroup.client.app.api.OrderApi;
import com.rntgroup.client.app.dto.OrderCreateRequestDto;
import com.rntgroup.client.app.dto.OrderDto;
import com.rntgroup.client.app.enumerate.OrderStatus;
import com.rntgroup.client.app.mapper.OrderMapper;
import com.rntgroup.client.app.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController implements OrderApi {

    OrderService orderService;
    OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(OrderCreateRequestDto orderRequestDto) {
        OrderDto orderDto = orderMapper.toDto(orderRequestDto);

        return orderService.createOrder(orderDto);
    }

    @Override
    public OrderStatus getStatus(Long id) {
        return orderService.getOrderById(id).getStatus();
    }
}
