package com.example.phonebook.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PhoneNumberDto {

    private String number;

    private UUID userId;
}
