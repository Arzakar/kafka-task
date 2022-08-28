package com.rntgroup.client.app.event;

import com.rntgroup.client.app.enumerate.OrderStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStatusChangeEvent {

    Long orderId;
    Long pizzaId;
    OrderStatus prevOrderStatus;
    OrderStatus newOrderStatus;

}
