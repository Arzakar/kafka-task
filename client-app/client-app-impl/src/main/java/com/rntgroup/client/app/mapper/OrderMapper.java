package com.rntgroup.client.app.mapper;

import com.rntgroup.client.app.dto.OrderCreateRequestDto;
import com.rntgroup.client.app.dto.OrderDto;
import com.rntgroup.client.app.entity.Order;
import com.rntgroup.client.app.event.OrderCreateEvent;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderDto toDto(Order order);

    OrderDto toDto(OrderCreateRequestDto orderCreateRequestDto);

    Order toEntity(OrderDto orderDto);

    OrderCreateEvent toEvent(Order order);

}
