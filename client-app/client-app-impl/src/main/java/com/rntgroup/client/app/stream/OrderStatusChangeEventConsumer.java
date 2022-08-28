package com.rntgroup.client.app.stream;

import com.rntgroup.client.app.enumerate.OrderStatus;
import com.rntgroup.client.app.event.OrderStatusChangeEvent;
import com.rntgroup.client.app.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderStatusChangeEventConsumer implements Consumer<Message<OrderStatusChangeEvent>> {

    OrderService orderService;

    @Override
    public void accept(Message<OrderStatusChangeEvent> inputMessage) {
        log.info("Получено сообщение об изменении статуса заказа: {}", inputMessage.getPayload());
        var orderStatusChangeEvent = inputMessage.getPayload();

        if (!orderStatusChangeEvent.getNewOrderStatus().equals(OrderStatus.PENDING)) {
            orderService.updateOrderStatus(orderStatusChangeEvent);
        }
    }
}
