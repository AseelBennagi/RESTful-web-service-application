package com.example.app.service.impl;

import com.example.app.entity.UserEntity;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserService;
import com.example.app.shared.Utils;
import com.example.app.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;


    @Override
    public UserDto createUser(UserDto user) {
        UserDto returnedValue = new UserDto();
        if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Email already used");
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String userId = utils.generateUserId(20);
        userEntity.setUserId(userId);

        UserEntity storedValue = userRepository.save(userEntity);

        BeanUtils.copyProperties(storedValue, returnedValue);

        return returnedValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserDto returnedValue = new UserDto();
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new RuntimeException("No user found!");

        BeanUtils.copyProperties(userEntity, returnedValue);

        return returnedValue;
    }

    @Override
    public UserDto getUserByUserId(String id) {
        UserDto returnedValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) throw new RuntimeException("User not found");

        BeanUtils.copyProperties(userEntity, returnedValue);

        return returnedValue;
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        UserDto returnedValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) throw new RuntimeException("User not found");
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        UserEntity updatedValue = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedValue, returnedValue);

        return returnedValue;
    }

    @Override
    public void deleteUser(String id) {
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) throw new RuntimeException("User not found");

        userRepository.delete(userEntity);


    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnedValue = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);
        Page<UserEntity> pages = userRepository.findAll(pageable);
        List<UserEntity> entities = pages.getContent();
        for (UserEntity userEntity : entities) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            returnedValue.add(userDto);
        }
        return returnedValue;
    }
}
