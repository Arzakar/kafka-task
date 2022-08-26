package com.rntgroup.client.app.stream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum KafkaBinding {

    ORDER_CREATE_CHANNEL("orderCreateChannel-in-0", "orderCreateChannel-out-0"),
    ORDER_STATUS_CHANGE_CHANNEL("orderStatusChangeChannel-in-0", "orderStatusChangeChannel-out-0");

    private final String input;
    private final String output;
}
