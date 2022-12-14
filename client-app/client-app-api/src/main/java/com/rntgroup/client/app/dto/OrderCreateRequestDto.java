package com.rntgroup.client.app.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreateRequestDto {

    Long customerId;
    Long pizzaId;

}
