package org.example.library.mapper;

import org.example.library.dtos.UserDto;
import org.example.library.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto,User> {
    UserDto toDto(User entity);
  //  @Mapping(target = "roles", ignore = true)
    User toEntity(UserDto dto);
}
