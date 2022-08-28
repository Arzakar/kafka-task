package com.rntgroup.palmetto.app.stream;

import com.rntgroup.client.app.event.OrderStatusChangeEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderStatusChangeEventSender {

    StreamBridge streamBridge;

    public void send(OrderStatusChangeEvent orderStatusChangeEvent) {
        Message<OrderStatusChangeEvent> message = MessageBuilder
                .withPayload(orderStatusChangeEvent)
                .build();
        boolean orderStatusChangeEventIsSent = streamBridge.send("orderStatusChanged-out-0", message);

        if (!orderStatusChangeEventIsSent) {
            throw new RuntimeException("Ошибка при изменении статуса заказа");
        }

        log.info("Статус заказа изменён: {}", message.getPayload());
    }

}
