package com.martijn.CompCheckV2.rest.dto.requests;

public class UserRegistrationRequestFactory {
    public static UserRegisterRequest.UserRegisterRequestBuilder userRegisterRequest() {
        return new UserRegisterRequest.UserRegisterRequestBuilder()
                .firstName("John")
                .lastName("Doe")
                .city("Amsterdam")
                .country("The Netherlands")
                .email("john.doe@gmail.com")
                .password("secret");
    }
}
