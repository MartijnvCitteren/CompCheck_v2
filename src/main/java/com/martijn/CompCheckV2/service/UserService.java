package com.martijn.CompCheckV2.service;

import com.martijn.CompCheckV2.exceptions.custom.EmailAlreadyExistsException;
import com.martijn.CompCheckV2.exceptions.custom.EmailNotFoundException;
import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.presistence.repository.UserRepository;
import com.martijn.CompCheckV2.rest.dto.UserDto;
import com.martijn.CompCheckV2.rest.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public UserDto registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.email())) {
            throw new EmailAlreadyExistsException();
        }
        User newUser = UserMapper.dtoToUser(userDto);
        newUser.setPassword(new BCryptPasswordEncoder().encode(userDto.password()));
        newUser = userRepository.save(newUser);
        return UserMapper.userToUserDto(newUser);
    }

    public UserDto findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.map(UserMapper::userToUserDto).orElseThrow(EmailNotFoundException::new);
    }

}
