package com.cakes.store.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserData toDTO(User user);

    User toEntity(UserData dto);
}
