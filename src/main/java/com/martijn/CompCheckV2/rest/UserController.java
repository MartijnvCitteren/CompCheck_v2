package com.martijn.CompCheckV2.rest;

import com.martijn.CompCheckV2.rest.dto.UserDto;
import com.martijn.CompCheckV2.rest.dto.requests.UserRegisterRequest;
import com.martijn.CompCheckV2.rest.dto.response.UserRegisterResponse;
import com.martijn.CompCheckV2.rest.mapper.UserMapper;
import com.martijn.CompCheckV2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        UserDto newUser = userService.registerUser(UserMapper.requestToDto(userRegisterRequest));
        return new ResponseEntity<>((UserMapper.userDtoToResponse(newUser)), HttpStatus.CREATED);
    }
}
