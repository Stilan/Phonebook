package com.example.phonebook.service;

import com.example.phonebook.dto.PhoneNumberDto;
import com.example.phonebook.dto.UserDto;
import com.example.phonebook.entity.PhoneNumber;
import com.example.phonebook.entity.User;
import com.example.phonebook.mapper.PhoneNumberMapper;
import com.example.phonebook.repository.PhoneNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;

    private final PhoneNumberMapper phoneNumberMapper;

    public PhoneNumber createPhoneNumber(PhoneNumberDto phoneNumberDto) {
        PhoneNumber savePhoneNumber = phoneNumberMapper.toEntity(phoneNumberDto);
        return phoneNumberRepository.save(savePhoneNumber);
    }

    public PhoneNumber getPhoneNumberById(UUID id) {
        return phoneNumberRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
    }

    public PhoneNumberDto updatePhoneNumber(UUID id, PhoneNumberDto phoneNumberDto) {
        PhoneNumber updatePhoneNumber = phoneNumberMapper.toEntity(phoneNumberDto);
        updatePhoneNumber.setId(id);
        return phoneNumberMapper.toDto(updatePhoneNumber);
    }

    public void deletePhoneNumber(UUID id) {
        PhoneNumber phoneNumberDelete = phoneNumberRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
        phoneNumberDelete.setDeleted(true);
        phoneNumberDelete.setDateOfDeletion(LocalDate.now());
        phoneNumberRepository.save(phoneNumberDelete);
    }

    public Page<PhoneNumber> findAll(Pageable pageable,  String phoneNumberPart, LocalDate date, LocalDate endDate) {
        return phoneNumberRepository.findAllBy(pageable, phoneNumberPart, date, endDate);
    }



}
