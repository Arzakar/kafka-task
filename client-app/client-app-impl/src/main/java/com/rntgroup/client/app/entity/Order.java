package com.rntgroup.client.app.entity;

import com.rntgroup.client.app.enumerate.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "customer_id", nullable = false)
    Long customerId;

    @Column(name = "pizza_id", nullable = false)
    Long pizzaId;

    @Column(name = "courier_id")
    Long courierId;

    @Column(name = "status", nullable = false)
    OrderStatus status;
}
