package com.martijn.CompCheckV2.presistence.entity;

public class UserFactory {

    public static User.UserBuilder createUser() {
        return User.builder()
                .id(null)
                .firstName("John")
                .lastName("Doe")
                .city("Amsterdam")
                .country("The Netherlands")
                .email("john.doe@gmail.com")
                .password("secret");
    }
}
