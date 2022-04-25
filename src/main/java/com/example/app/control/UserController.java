package com.example.app.control;

import com.example.app.model.request.UserRequest;
import com.example.app.model.response.UserResponse;
import com.example.app.service.UserService;
import com.example.app.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        UserResponse returnedValue = new UserResponse();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnedValue);

        return returnedValue;
    }

//    @GetMapping(path = "{email}")
//    public UserResponse getUserByEmail(@PathVariable String email){
//        UserResponse returnedValue = new UserResponse();
//        UserDto userDto = userService.getUser(email);
//        BeanUtils.copyProperties(userDto, returnedValue);
//
//        return returnedValue;
//    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        UserResponse returnedValue = new UserResponse();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);

        UserDto storedValue = userService.createUser(userDto);

        BeanUtils.copyProperties(storedValue, returnedValue);

        return returnedValue;
    }


    @PutMapping(path = "/{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
        UserResponse returnedValue = new UserResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);

        UserDto updatedValue = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updatedValue, returnedValue);

        return returnedValue;
    }


    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public List<UserResponse> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "limit", defaultValue = "1") int limit) {

        List<UserResponse> returnedValue = new ArrayList<>();
        List<UserDto> userDtos = userService.getUsers(page, limit);
        for (UserDto userDto : userDtos) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(userDto, userResponse);
            returnedValue.add(userResponse);
        }

        return returnedValue;

    }


}
