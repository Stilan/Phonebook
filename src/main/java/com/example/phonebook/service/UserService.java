package com.example.phonebook.service;

import com.example.phonebook.dto.UserDto;
import com.example.phonebook.entity.PhoneNumber;
import com.example.phonebook.entity.User;
import com.example.phonebook.mapper.UserMapper;
import com.example.phonebook.repository.PhoneNumberRepository;
import com.example.phonebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final PhoneNumberRepository phoneNumberRepository;

    public User createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User saveUser = userMapper.toEntity(userDto);
        return userRepository.save(saveUser);
    }

    public User getUserDtoById(UUID id) {
       return userRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
    }

    public UserDto updateUsers(UUID id, UserDto userDto) {
        User updateUser = userMapper.toEntity(userDto);
        updateUser.setId(id);
        userRepository.save(updateUser);
        return userMapper.toDto(updateUser);
    }

    public void deleteUser(UUID id) {
        User userDelete = userRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
        userDelete.setDeleted(true);
        userRepository.save(userDelete);
    }

    public List<PhoneNumber> getAllPhoneNumber(UUID idUser) {
        return phoneNumberRepository.getAllByUserId(idUser);
    }

}
