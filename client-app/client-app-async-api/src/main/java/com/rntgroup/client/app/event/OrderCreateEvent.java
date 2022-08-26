package com.rntgroup.client.app.event;

import lombok.Data;

@Data
public class OrderCreateEvent {

    Long id;
    Long pizzaId;

}
