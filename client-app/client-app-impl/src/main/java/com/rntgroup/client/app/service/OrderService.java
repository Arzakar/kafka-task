package com.rntgroup.client.app.service;

import com.rntgroup.client.app.dto.OrderDto;
import com.rntgroup.client.app.entity.Order;
import com.rntgroup.client.app.enumerate.OrderStatus;
import com.rntgroup.client.app.mapper.OrderMapper;
import com.rntgroup.client.app.repository.OrderRepository;
import com.rntgroup.client.app.stream.OrderCreateEventSender;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    OrderCreateEventSender orderCreateEventSender;

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto.setStatus(OrderStatus.PENDING));
        Order createdOrder = orderRepository.save(order);

        orderCreateEventSender.send(orderMapper.toEvent(order));

        return orderMapper.toDto(createdOrder);
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));

        return orderMapper.toDto(order);
    }
}
