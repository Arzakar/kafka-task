package com.rntgroup.palmetto.app.stream;

import com.rntgroup.client.app.enumerate.OrderStatus;
import com.rntgroup.client.app.event.OrderCreateEvent;
import com.rntgroup.client.app.event.OrderStatusChangeEvent;
import com.rntgroup.palmetto.app.service.PizzaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderCreateEventProcessor implements Function<Message<OrderCreateEvent>, Message<OrderStatusChangeEvent>> {

    PizzaService pizzaService;

    @Override
    public Message<OrderStatusChangeEvent> apply(Message<OrderCreateEvent> inputMessage) {
        var orderCreateEvent = inputMessage.getPayload();

        pizzaService.cookPizza(orderCreateEvent);
        log.info(String.format("Заказ с id = %s начал готовиться", orderCreateEvent.getId()));

        var orderStatusChangeEvent = new OrderStatusChangeEvent()
                .setOrderId(orderCreateEvent.getId())
                .setPizzaId(orderCreateEvent.getPizzaId())
                .setPrevOrderStatus(OrderStatus.PENDING)
                .setNewOrderStatus(OrderStatus.COOKING)
                .setCorrelationId(orderCreateEvent.getCorrelationId());

        return MessageBuilder
                .withPayload(orderStatusChangeEvent)
                .setHeader(KafkaHeaders.MESSAGE_KEY, orderStatusChangeEvent.getCorrelationId())
                .build();
    }
}
