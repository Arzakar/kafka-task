package com.rntgroup.client.app.stream;

import com.rntgroup.client.app.event.OrderCreateEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderCreateEventSender {

    StreamBridge streamBridge;

    public void send(OrderCreateEvent orderCreateEvent) {
        String correlationId = UUID.randomUUID().toString();
        Message<OrderCreateEvent> message = MessageBuilder
                .withPayload(orderCreateEvent.setCorrelationId(correlationId))
                .setHeader(KafkaHeaders.MESSAGE_KEY, correlationId)
                .build();
        boolean orderCreateEventIsSent = streamBridge.send("orderCreated-out-0", message);

        if (!orderCreateEventIsSent) {
            throw new RuntimeException("Ошибка при отправке заказа в пиццерию");
        }

        log.info("Заказ отправлен в пиццерию: {}", message.getPayload());
    }

}
