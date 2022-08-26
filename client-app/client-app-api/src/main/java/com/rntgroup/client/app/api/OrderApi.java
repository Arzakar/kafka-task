package com.rntgroup.client.app.api;

import com.rntgroup.client.app.dto.OrderCreateRequestDto;
import com.rntgroup.client.app.dto.OrderDto;
import com.rntgroup.client.app.enumerate.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "orders-api", description = "Методы взаимодействия с Order")
@RequestMapping("/api/v1/orders")
public interface OrderApi {

    @Operation(summary = "Создать новый заказ")
    @PostMapping
    OrderDto createOrder(@RequestBody OrderCreateRequestDto orderRequestDto);

    @Operation(summary = "Получить статус заказа")
    @GetMapping(path = "/{id}/status")
    OrderStatus getStatus(@PathVariable("id") Long id);

}
