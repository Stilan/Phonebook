package com.example.phonebook.repository;

import com.example.phonebook.entity.PhoneNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, UUID> {

    Optional<PhoneNumber> findByIdAndIsDeletedFalse(UUID id);

    @Query("SELECT u FROM PhoneNumber u WHERE u.number like CONCAT('%',:phoneNumberPart,'%')  and u.dateOfCreation >= :startDate AND u.dateOfCreation <= :endDate")
    Page<PhoneNumber> findAllBy(Pageable pageable, @Param("phoneNumberPart") String phoneNumberPart,@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);







}
