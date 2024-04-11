package com.martijn.CompCheckV2.service;

import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.presistence.entity.UserFactory;
import com.martijn.CompCheckV2.presistence.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;


    @Test
    void givenARegistrantsEmailDoesNOTExist_whenRegistering_thenSaveUserInDBAndReturnUser() {
        //given
        User registrant = UserFactory.createUser().build();
        User expectedUser = UserFactory.createUser()
                .id(1L)
                .build();

        //when
        when(userRepository.existsByEmail(registrant.getEmail())).thenReturn(false);
        when(userRepository.save(registrant)).thenReturn(expectedUser);
        User result = userService.registerUser(registrant);

        //then
        assertEquals(expectedUser.getId(), result.getId());
        assertEquals(expectedUser.getEmail(), result.getEmail());
        assertEquals(expectedUser.getFirstName(), result.getFirstName());
        assertEquals(expectedUser.getPassword(), result.getPassword());
    }

    //TODO given email exists
    @Test
    void givenARegistrantsEmailDOESExist_whenRegistering_thenThrowException() {
        //given
        User registrant = UserFactory.createUser().build();

        //when
        when(userRepository.existsByEmail(registrant.getEmail())).thenReturn(true);

        //then
        assertThrows(EntityExistsException.class,() -> userService.registerUser(registrant));
    }

}