package com.rntgroup.palmetto.app.entity;

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
@Table(name = "pizzas")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "compound", nullable = false)
    String compound;

    @Column(name = "diameter", nullable = false)
    Integer diameter;

    @Column(name = "mass", nullable = false)
    Integer mass;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "cooking_duration", nullable = false)
    Integer cookingDuration;
}
