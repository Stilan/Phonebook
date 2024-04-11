package com.example.phonebook.repository;

import com.example.phonebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByIdAndIsDeletedFalse(UUID id);

}
