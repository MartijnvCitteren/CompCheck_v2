package com.martijn.CompCheckV2.rest.mapper;

import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.rest.dto.UserDto;
import com.martijn.CompCheckV2.rest.dto.requests.UserRequest;
import com.martijn.CompCheckV2.rest.dto.response.UserResponse;

public class UserMapper {

    public static UserDto requestToDto(UserRequest request) {
        return UserDto.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .city(request.city())
                .country(request.country())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse userDtoToResponse(UserDto user){
        return UserResponse.builder()
                .firstName(user.firstName())
                .lastName(user.lastName())
                .city(user.city())
                .country(user.country())
                .email(user.email())
                .build();
    }

    public static User dtoToUser(UserDto dto){
        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .city(dto.city())
                .country(dto.country())
                .email(dto.email())
                .password(dto.password())
                .build();
    }
}
