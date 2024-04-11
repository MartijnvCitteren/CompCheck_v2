package com.martijn.CompCheckV2.service;

import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.presistence.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(User user){
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException("This e-mail already exists");
        }

        return userRepository.save(user);
    }
}
