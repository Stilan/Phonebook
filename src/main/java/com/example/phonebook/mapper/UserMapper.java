package com.example.phonebook.mapper;

import com.example.phonebook.dto.UserDto;
import com.example.phonebook.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
