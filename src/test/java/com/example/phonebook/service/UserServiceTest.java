package com.example.phonebook.service;

import com.example.phonebook.dto.UserDto;
import com.example.phonebook.entity.User;
import com.example.phonebook.mapper.UserMapper;
import com.example.phonebook.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  UserMapper userMapper;

    @Test
    void createUser() {
        UserDto userDto = new UserDto();
        userDto.setName("Jon");
        User user = new User();
        user.setId(UUID.fromString("57f3f742-d940-46a5-bde4-df39a49525eb"));
        user.setName("Jon");
        Mockito.when(userRepository.save(any())).thenReturn(user);
        Mockito.when(userMapper.toEntity(userDto)).thenReturn(user);
        User result = userService.createUser(userDto);
        Assertions.assertEquals("Jon", result.getName());

    }

    @Test
    void getUserDtoById() {
        User user = new User();
        user.setId(UUID.fromString("57f3f742-d940-46a5-bde4-df39a49525eb"));
        user.setName("Jon");
        Mockito.when(userRepository.findByIdAndIsDeletedFalse(UUID.fromString("57f3f742-d940-46a5-bde4-df39a49525eb")))
                .thenReturn(Optional.of(user));
        User result = userService.getUserDtoById(UUID.fromString("57f3f742-d940-46a5-bde4-df39a49525eb"));
        Assertions.assertEquals("Jon", result.getName());
    }

    @Test
    void updateUsers() {
        UserDto userDto = new UserDto();
        userDto.setName("Bob");
        User user = new User();
        user.setId(UUID.fromString("57f3f742-d940-46a5-bde4-df39a49525eb"));
        user.setName("Jon");
        Mockito.when(userMapper.toEntity(userDto)).thenReturn(user);
        Mockito.when(userMapper.toDto(user)).thenReturn(userDto);
        UserDto result = userService.updateUsers(UUID.fromString("57f3f742-d940-46a5-bde4-df39a49525eb"), userDto);
        Assertions.assertEquals("Bob", result.getName());
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setId(UUID.fromString("57f3f742-d940-46a5-bde4-df39a49525eb"));
        user.setName("Jon");
        Mockito.when(userRepository.findByIdAndIsDeletedFalse(UUID.fromString("57f3f742-d940-46a5-bde4-df39a49525eb")))
                .thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(any())).thenReturn(user);
        userService.deleteUser(UUID.fromString("57f3f742-d940-46a5-bde4-df39a49525eb"));
        assertTrue(user.isDeleted());
    }
}