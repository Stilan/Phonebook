package com.example.phonebook.service;


import com.example.phonebook.entity.User;
import com.example.phonebook.entity.UserLoginDetails;
import com.example.phonebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserLoginDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String name) {

        User user = userRepository.getUserByName(name).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found? ", name)));
        return new UserLoginDetails(user);
    }
    public UUID getIdUser(String name) {
        User user = userRepository.getUserByName(name).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found? ", name)));
        return user.getId();
    }

}
