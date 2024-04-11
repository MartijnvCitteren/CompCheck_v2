package com.martijn.CompCheckV2.rest.mapper;

import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.rest.requests.UserRequest;
import com.martijn.CompCheckV2.rest.response.UserResponse;

public class UserMapper {

    public static User requestToUser(UserRequest request) {
        return User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .city(request.city())
                .country(request.country())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse userToResponse(User user){
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .city(user.getCity())
                .country(user.getCountry())
                .email(user.getEmail())
                .build();
    }
}
