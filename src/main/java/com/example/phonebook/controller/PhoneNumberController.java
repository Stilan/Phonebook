package com.example.phonebook.controller;

import com.example.phonebook.dto.PhoneNumberDto;
import com.example.phonebook.dto.UserDto;
import com.example.phonebook.entity.PhoneNumber;
import com.example.phonebook.entity.User;
import com.example.phonebook.service.PhoneNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Tag(name = "PhoneNumber_methods")
@RestController
@RequiredArgsConstructor
@RequestMapping("phoneNumber")
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

    @Operation(
            summary = "Создания и сохранения  PhoneNumber",
            description = "Получает PhoneNumberDto и собирает его с "
                    + "помощью mapper и сохраняет его в бд выводит PhoneNumber"
    )
    @PostMapping("/create")
    public ResponseEntity<PhoneNumber> createPhoneNumber(@RequestBody PhoneNumberDto phoneNumberDto) {
        PhoneNumber phoneNumber = phoneNumberService.createPhoneNumber(phoneNumberDto);
        return new ResponseEntity<>(phoneNumber, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Поиск по id PhoneNumber",
            description = "Принимает id и возвращает PhoneNumber"
    )
    @GetMapping("{id}")
    public ResponseEntity<PhoneNumber> getPhoneNumberById(@PathVariable UUID id) {
        PhoneNumber phoneNumber = phoneNumberService.getPhoneNumberById(id);
        return ResponseEntity.ok(phoneNumber);
    }
    @Operation(
            summary = "Обновляет PhoneNumber",
            description = "Принимает id и PhoneNumberDto c обновлёнными данными"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<PhoneNumberDto> updatePhoneNumber(@PathVariable UUID id, @RequestBody PhoneNumberDto phoneNumberDto) {
        PhoneNumberDto phoneNumberDtoUpdate = phoneNumberService.updatePhoneNumber(id, phoneNumberDto);
        return new ResponseEntity<>(phoneNumberDtoUpdate, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Удаляет из бд PhoneNumber",
            description = "Принимает id"
    )
    @DeleteMapping("/delete/{id}")
    public void deleteStock(@PathVariable UUID id) {
        phoneNumberService.deletePhoneNumber(id);
    }

    @Operation(
            summary = "Фильтор ",
            description = "Принимает id"
    )
    @GetMapping("/sort")
    public ResponseEntity<Page<PhoneNumber>> sortPhoneNumber(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "3") int size,
                                                             @RequestParam(defaultValue = "") String phoneNumberPart,
                                                             @RequestParam(defaultValue = "2024-01-31")
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                             @RequestParam(defaultValue = "2024-12-31")
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(phoneNumberService.findAll(pageable, phoneNumberPart, date, endDate));
    }

}
