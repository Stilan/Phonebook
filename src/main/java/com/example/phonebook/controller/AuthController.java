package com.example.phonebook.controller;

import com.example.phonebook.entity.PhoneNumber;
import com.example.phonebook.entity.User;
import com.example.phonebook.repository.UserRepository;
import com.example.phonebook.service.AuthService;
import com.example.phonebook.service.UserLoginDetailsService;
import com.example.phonebook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final UserLoginDetailsService userLoginDetails;
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public ResponseEntity<String> loginPerson(@RequestBody User user)  {
        User y = userRepository.getUserByName(user.getName()).orElseThrow();
        System.out.println(y.getPassword());
        UserDetails personDetails = userLoginDetails.loadUserByUsername(user.getName());
        System.out.println(user.getPassword());
        System.out.println(personDetails.getPassword());
        if (passwordEncoder.matches(user.getPassword(), personDetails.getPassword())) {
            System.out.println(personDetails.getUsername());
            List<PhoneNumber> phoneNumbers = userService.getAllPhoneNumber(userLoginDetails.getIdUser(personDetails.getUsername()));
            StringBuilder str = new StringBuilder();
            for (PhoneNumber phoneNumber : phoneNumbers) {
                str.append(phoneNumber.getNumber() + " ");
            }
            return ResponseEntity.status(HttpStatus.OK).body("User numbers "+ str + "\nPerson logged successfully \n Token: " +  authService.authentication(user));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person failed to login");

    }
}
