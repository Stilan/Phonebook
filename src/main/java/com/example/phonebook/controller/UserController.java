package com.example.phonebook.controller;

import com.example.phonebook.dto.UserDto;
import com.example.phonebook.entity.PhoneNumber;
import com.example.phonebook.entity.User;
import com.example.phonebook.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @GetMapping("/PhoneNumber/{idUser}")
    public ResponseEntity<List<PhoneNumber>> getPhoneNumber(@PathVariable UUID idUser) {
        return new ResponseEntity<>(userService.getAllPhoneNumber(idUser), HttpStatus.CREATED);
    }

}
