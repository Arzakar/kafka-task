package com.rntgroup.client.app.dto;

import com.rntgroup.client.app.enumerate.OrderStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {

    Long id;
    Long customerId;
    Long pizzaId;
    Long courierId;
    OrderStatus status;

}
