package com.martijn.CompCheckV2.rest.mapper;

import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.rest.dto.UserDto;
import com.martijn.CompCheckV2.rest.dto.requests.UserRegisterRequest;
import com.martijn.CompCheckV2.rest.dto.response.UserRegisterResponse;

public class UserMapper {
    private UserMapper() {
    }

    public static UserDto requestToDto(UserRegisterRequest request) {
        return UserDto.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .city(request.city())
                .country(request.country())
                .email(request.email())
                .password(request.password())
                .role(request.role())
                .build();
    }

    public static UserRegisterResponse userDtoToResponse(UserDto user) {
        return UserRegisterResponse.builder()
                .firstName(user.firstName())
                .lastName(user.lastName())
                .city(user.city())
                .country(user.country())
                .email(user.email())
                .build();
    }

    public static User dtoToUser(UserDto dto) {
        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .city(dto.city())
                .country(dto.country())
                .email(dto.email())
                .password(dto.password())
                .role(dto.role())
                .build();
    }

    public static UserDto userToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .city(user.getCity())
                .country(user.getCountry())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
