package com.example.phonebook.controller;

import com.example.phonebook.dto.UserDto;
import com.example.phonebook.entity.User;
import com.example.phonebook.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User_methods")
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Создания и сохранения  User",
            description = "Получает userDto и собирает его с "
                    + "помощью mapper и сохраняет его в бд выводит User"
    )
    @PostMapping("/create")
    public ResponseEntity<User> createStock(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Поиск по id User",
            description = "Принимает id и возвращает User"
    )
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.getUserDtoById(id);
        return ResponseEntity.ok(user);
    }
    @Operation(
            summary = "Обновляет User",
            description = "Принимает id и UserDto c обновлёнными данными"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
        UserDto updateUser = userService.updateUsers(id, userDto);
        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Удаляет из бд User",
            description = "Принимает id"
    )
    @DeleteMapping("/delete/{id}")
    public void deleteStock(@PathVariable UUID id) {
       userService.deleteUser(id);
    }

}
