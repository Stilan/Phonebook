package com.example.phonebook.service;

import com.example.phonebook.dto.PhoneNumberDto;
import com.example.phonebook.entity.PhoneNumber;
import com.example.phonebook.mapper.PhoneNumberMapper;
import com.example.phonebook.repository.PhoneNumberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PhoneNumberServiceTest {

    @InjectMocks
    private PhoneNumberService phoneNumberService;
    @Mock
    private PhoneNumberRepository phoneNumberRepository;
    @Mock
    private PhoneNumberMapper phoneNumberMapper;

    @Test
    void createPhoneNumber() {
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.setNumber("89137753322");
        phoneNumberDto.setUserId(UUID.fromString("57f3f742-d940-46a5-bde4-df39a49525eb"));
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(UUID.fromString("600e113a-40c3-48f9-aa1d-7fd51d6a4743"));
        phoneNumber.setNumber("89137753322");
        Mockito.when(phoneNumberRepository.save(any())).thenReturn(phoneNumber);
        Mockito.when(phoneNumberMapper.toEntity(phoneNumberDto)).thenReturn(phoneNumber);
        PhoneNumber result = phoneNumberService.createPhoneNumber(phoneNumberDto);
        Assertions.assertEquals("89137753322", result.getNumber());
    }

    @Test
    void getPhoneNumberById(){
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(UUID.fromString("600e113a-40c3-48f9-aa1d-7fd51d6a4743"));
        phoneNumber.setNumber("89137753322");
        Mockito.when(phoneNumberRepository.findByIdAndIsDeletedFalse(UUID.fromString("600e113a-40c3-48f9-aa1d-7fd51d6a4743")))
                .thenReturn(Optional.of(phoneNumber));
        PhoneNumber result = phoneNumberService.getPhoneNumberById(UUID.fromString("600e113a-40c3-48f9-aa1d-7fd51d6a4743"));
        Assertions.assertEquals("89137753322", result.getNumber());
    }
    @Test
    void updatePhoneNumber(){
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.setNumber("891377533333");
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(UUID.fromString("691bfcf8-9068-4623-ae2c-2fab9f237ff6"));
        phoneNumber.setNumber("89137753322");
        Mockito.when(phoneNumberMapper.toEntity(phoneNumberDto)).thenReturn(phoneNumber);
        Mockito.when(phoneNumberMapper.toDto(phoneNumber)).thenReturn(phoneNumberDto);
        PhoneNumberDto result = phoneNumberService.updatePhoneNumber
                (UUID.fromString("691bfcf8-9068-4623-ae2c-2fab9f237ff6"),phoneNumberDto);
        Assertions.assertEquals("891377533333", result.getNumber());
    }

    @Test
    void deletePhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(UUID.fromString("691bfcf8-9068-4623-ae2c-2fab9f237ff6"));
        phoneNumber.setNumber("89137753322");
        Mockito.when(phoneNumberRepository.findByIdAndIsDeletedFalse(UUID.fromString("691bfcf8-9068-4623-ae2c-2fab9f237ff6")))
                .thenReturn(Optional.of(phoneNumber));
        Mockito.when(phoneNumberRepository.save(any())).thenReturn(phoneNumber);
        phoneNumberService.deletePhoneNumber(UUID.fromString("691bfcf8-9068-4623-ae2c-2fab9f237ff6"));
        assertTrue(phoneNumber.getIsDeleted());
    }
}