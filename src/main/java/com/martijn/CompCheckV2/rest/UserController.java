package com.martijn.CompCheckV2.rest;

import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.rest.mapper.UserMapper;
import com.martijn.CompCheckV2.rest.requests.UserRequest;
import com.martijn.CompCheckV2.rest.response.UserResponse;
import com.martijn.CompCheckV2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/compcheck/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody @Valid UserRequest userRequest){
        User newUser = userService.registerUser(UserMapper.requestToUser(userRequest));
        return UserMapper.userToResponse(newUser);
    }

}
