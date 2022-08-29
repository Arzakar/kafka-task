package com.rntgroup.palmetto.app.service;

import com.rntgroup.client.app.enumerate.OrderStatus;
import com.rntgroup.client.app.event.OrderCreateEvent;
import com.rntgroup.client.app.event.OrderStatusChangeEvent;
import com.rntgroup.palmetto.app.dto.PizzaDto;
import com.rntgroup.palmetto.app.entity.Pizza;
import com.rntgroup.palmetto.app.mapper.PizzaMapper;
import com.rntgroup.palmetto.app.repository.PizzaRepository;

import com.rntgroup.palmetto.app.stream.OrderStatusChangeEventSender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PizzaService {

    PizzaRepository pizzaRepository;
    PizzaMapper pizzaMapper;

    OrderStatusChangeEventSender orderStatusChangeEventSender;

    public PizzaDto getPizzaById(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return pizzaMapper.toDto(pizza);
    }

    public void cookPizza(OrderCreateEvent orderCreateEvent) {
        new Thread(() -> {
            try {
                Long orderId = orderCreateEvent.getId();
                Long pizzaId = orderCreateEvent.getPizzaId();
                String correlationId = orderCreateEvent.getCorrelationId();

                Integer cookingDuration = getPizzaById(pizzaId).getCookingDuration();
                Thread.sleep(Duration.ofSeconds(cookingDuration).toMillis());

                var orderIsCooked = new OrderStatusChangeEvent()
                        .setOrderId(orderId)
                        .setPizzaId(pizzaId)
                        .setPrevOrderStatus(OrderStatus.COOKING)
                        .setNewOrderStatus(OrderStatus.READY)
                        .setCorrelationId(correlationId);

                orderStatusChangeEventSender.send(orderIsCooked);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
