package com.example.app.service;

import com.example.app.shared.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUser(String email);

    UserDto getUserByUserId(String id);

    UserDto updateUser(String id, UserDto userDto);

    void deleteUser(String id);
    List<UserDto> getUsers(int page, int limit);
}
