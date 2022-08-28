package com.rntgroup.palmetto.app.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PizzaDto {

    Long id;
    String name;
    String compound;
    Integer diameter;
    Integer mass;
    Double price;
    Integer cookingDuration;

}
