package com.rntgroup.courier.app.stream;

import com.rntgroup.client.app.enumerate.OrderStatus;
import com.rntgroup.client.app.event.OrderStatusChangeEvent;
import com.rntgroup.courier.app.service.SupplierService;
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

    SupplierService supplierService;
    OrderStatusChangeEventSender orderStatusChangeEventSender;

    @Override
    public void accept(Message<OrderStatusChangeEvent> inputMessage) {
        var currentStatusChangeEvent = inputMessage.getPayload();

        if (currentStatusChangeEvent.getNewOrderStatus().equals(OrderStatus.READY)) {
            supplierService.supply(currentStatusChangeEvent);
            log.info(String.format("Заказ с id = %s доставляется курьером", currentStatusChangeEvent.getOrderId()));

            var newStatusChangeEvent = currentStatusChangeEvent
                    .setPrevOrderStatus(OrderStatus.READY)
                    .setNewOrderStatus(OrderStatus.IN_WAY);

            orderStatusChangeEventSender.send(newStatusChangeEvent);
        }
    }
}
