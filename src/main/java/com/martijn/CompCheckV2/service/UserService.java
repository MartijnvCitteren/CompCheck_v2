package com.martijn.CompCheckV2.service;

import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.presistence.repository.UserRepository;
import com.martijn.CompCheckV2.rest.dto.UserDto;
import com.martijn.CompCheckV2.rest.mapper.UserMapper;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto registerUser(UserDto userDto){
        if(userRepository.existsByEmail(userDto.email())) {
            throw new EntityExistsException("This e-mail address already exists");
        }
        User newUser = UserMapper.dtoToUser(userDto);
        newUser = userRepository.save(newUser);
        return UserMapper.userToUserDto(newUser);
    }
}
