package com.martijn.CompCheckV2.rest.dto.response;

public class UserRegistrationResponseFactory {
    public static UserRegisterResponse.UserRegisterResponseBuilder userRegisterResponse() {
        return new UserRegisterResponse.UserRegisterResponseBuilder()
                .firstName("John")
                .lastName("Doe")
                .city("Amsterdam")
                .country("The Netherlands")
                .email("john.doe@gmail.com");
    }
}
