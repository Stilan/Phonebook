package com.example.phonebook.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.phonebook.entity.User;
import com.example.phonebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    public static final String SECRET = "231123333";
    private final UserRepository userRepository;


    public String getToken(User user) {
        User userFromDB = userRepository
                .getUserByName(user.getName()).orElseThrow();

        return JWT.create()
                .withClaim("Name", userFromDB.getName())
                .withClaim("Password", userFromDB.getPassword())
                .withClaim("Id", String.valueOf(userFromDB.getId()))
                .sign(Algorithm.HMAC256(SECRET));
    }

}
