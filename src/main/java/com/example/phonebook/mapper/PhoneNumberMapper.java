package com.example.phonebook.mapper;

import com.example.phonebook.dto.PhoneNumberDto;
import com.example.phonebook.entity.PhoneNumber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneNumberMapper {

    PhoneNumberDto toDto(PhoneNumber phoneNumber);

    PhoneNumber toEntity(PhoneNumberDto dto);
}
