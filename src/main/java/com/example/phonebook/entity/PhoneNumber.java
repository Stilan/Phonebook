package com.example.phonebook.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();

    private String number;

    private LocalDate dateOfCreation;

    private LocalDate dateOfDeletion;

    private UUID userId;

    private boolean isDeleted = Boolean.FALSE;

    @PrePersist
    private void onCreate() {
        this.dateOfCreation = LocalDate.now();
    }
}
