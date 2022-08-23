package com.rntgroup.client.app.api;

import com.rntgroup.client.app.dto.OrderCreateRequestDto;
import com.rntgroup.client.app.dto.OrderDto;
import com.rntgroup.client.app.enumerate.OrderStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/orders")
public interface OrderApi {

    @PostMapping
    OrderDto createOrder(@RequestBody OrderCreateRequestDto orderRequestDto);

    @GetMapping(path = "/{id}/status")
    OrderStatus getStatus(@PathVariable("id") Long id);

}
