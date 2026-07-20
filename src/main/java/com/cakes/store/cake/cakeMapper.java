package com.cakes.store.cake;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface cakeMapper {

    CakeDTO toDTO(Cake cake);

    Cake toEntity(CakeDTO dto);
}
