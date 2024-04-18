package com.martijn.CompCheckV2.service;

import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.presistence.entity.UserFactory;
import com.martijn.CompCheckV2.presistence.repository.UserRepository;
import com.martijn.CompCheckV2.rest.dto.UserDto;
import com.martijn.CompCheckV2.rest.dto.UserDtoFactory;
import com.martijn.CompCheckV2.rest.mapper.UserMapper;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void givenARegistrantsEmailDoesNOTExist_whenRegistering_thenSaveUserInDBAndReturnUser() {
        //given
        UserDto registrantDto = UserDtoFactory.createUserDto().build();
        User thisUser = UserFactory.createUser()
               .id(1L)
               .build();
        UserDto expectedUserDto = UserDtoFactory.createUserDto()
                .id(1L)
                .build();

        //when
        when(userRepository.existsByEmail(registrantDto.email())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(thisUser);

        UserDto result = userService.registerUser(registrantDto);

        //then
        assertEquals(expectedUserDto.email(), result.email());
        assertEquals(expectedUserDto.firstName(), result.firstName());
        assertEquals(expectedUserDto.password(), result.password());
    }


    @Test
    void givenARegistrantsEmailDOESExist_whenRegistering_thenThrowException() {
        //given
        UserDto registrant = UserDtoFactory.createUserDto().build();

        //when
        when(userRepository.existsByEmail(registrant.email())).thenReturn(true);

        //then
        assertThrows(EntityExistsException.class,() -> userService.registerUser(registrant));
    }

}