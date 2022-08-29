package com.rntgroup.courier.app.service;

import com.rntgroup.client.app.enumerate.OrderStatus;
import com.rntgroup.client.app.event.OrderStatusChangeEvent;
import com.rntgroup.courier.app.stream.OrderStatusChangeEventSender;
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
public class SupplierService {

    OrderStatusChangeEventSender orderStatusChangeEventSender;

    public void supply(OrderStatusChangeEvent orderStatusChangeEvent) {
        new Thread(() -> {
            try {
                Long orderId = orderStatusChangeEvent.getOrderId();
                Long pizzaId = orderStatusChangeEvent.getPizzaId();
                String correlationId = orderStatusChangeEvent.getCorrelationId();

                int timeForSupply = (int) (Math.random() * 5) + 5;
                Thread.sleep(Duration.ofSeconds(timeForSupply).toMillis());

                var orderIsDelivered = new OrderStatusChangeEvent()
                        .setOrderId(orderId)
                        .setPizzaId(pizzaId)
                        .setPrevOrderStatus(OrderStatus.IN_WAY)
                        .setNewOrderStatus(OrderStatus.DELIVERED)
                        .setCorrelationId(correlationId);

                orderStatusChangeEventSender.send(orderIsDelivered);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
