package com.rntgroup.client.app.service;

import com.rntgroup.client.app.dto.OrderDto;
import com.rntgroup.client.app.entity.Order;
import com.rntgroup.client.app.enumerate.OrderStatus;
import com.rntgroup.client.app.event.OrderCreateEvent;
import com.rntgroup.client.app.mapper.OrderMapper;
import com.rntgroup.client.app.repository.OrderRepository;
import com.rntgroup.client.app.stream.KafkaBinding;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    StreamBridge streamBridge;

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto.setStatus(OrderStatus.PENDING));
        Order createdOrder = orderRepository.save(order);

        Message<OrderCreateEvent> message = MessageBuilder.withPayload(orderMapper.toEvent(order)).build();
        boolean orderIsSent = streamBridge.send(KafkaBinding.ORDER_CREATE_CHANNEL.getOutput(), message);

        if (!orderIsSent) {
            throw new RuntimeException("Error while sending order");
        }

        log.info("Order is sent: {}", message.getPayload());
        return orderMapper.toDto(createdOrder);
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));

        return orderMapper.toDto(order);
    }
}
