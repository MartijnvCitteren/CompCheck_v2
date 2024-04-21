package com.martijn.CompCheckV2.rest.dto;

public class UserDtoFactory {
    public static UserDto.UserDtoBuilder createUserDto() {
        return new UserDto.UserDtoBuilder()
                .id(null)
                .firstName("John")
                .lastName("Doe")
                .city("Amsterdam")
                .country("The Netherlands")
                .email("john.doe@gmail.com")
                .password("secret");
    }
}
