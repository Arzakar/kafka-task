package com.rntgroup.palmetto.app.mapper;

import com.rntgroup.palmetto.app.dto.PizzaDto;
import com.rntgroup.palmetto.app.entity.Pizza;
import org.mapstruct.Mapper;

@Mapper
public interface PizzaMapper {

    PizzaDto toDto(Pizza pizza);

}
