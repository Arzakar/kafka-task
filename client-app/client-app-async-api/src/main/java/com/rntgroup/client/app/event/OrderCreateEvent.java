package com.rntgroup.client.app.event;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreateEvent {

    Long id;
    Long pizzaId;
    String correlationId;

}
