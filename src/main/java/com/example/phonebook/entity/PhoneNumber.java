package com.example.phonebook.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    private String number;

    private LocalDate dateOfCreation;

    private LocalDate dateOfDeletion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isDeleted;

    @Transient
    public Boolean getIsDeleted() {
       return dateOfDeletion == null ? Boolean.FALSE : Boolean.TRUE;
    }

    @PrePersist
    private void onCreate() {
        this.dateOfCreation = LocalDate.now();
    }
}
