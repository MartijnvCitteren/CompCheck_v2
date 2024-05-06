package com.martijn.CompCheckV2.service;

import com.martijn.CompCheckV2.exceptions.custom.InvalidLoginCredentialsException;
import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.presistence.entity.UserFactory;
import com.martijn.CompCheckV2.presistence.repository.UserRepository;
import com.martijn.CompCheckV2.rest.dto.LoginDto;
import com.martijn.CompCheckV2.rest.dto.UserDto;
import com.martijn.CompCheckV2.rest.dto.UserDtoFactory;
import com.martijn.CompCheckV2.security.jwt.JwtService;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.print.DocFlavor;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;

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
        assertThrows(EntityExistsException.class, () -> userService.registerUser(registrant));
    }

    @Test
    void givenEmailExists_whenSearchingUserByMail_thenReturnUser() {
        //given
        String email = "test@test.com";
        User user = UserFactory.createUser().email(email).build();
        UserDto userDto = UserDtoFactory.createUserDto().email(email).build();

        //when
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        UserDto result = userService.findUserByEmail(email);

        //then
        assertEquals(userDto.email(), result.email());
        assertEquals(userDto.firstName(), result.firstName());
        assertEquals(userDto.password(), result.password());
        assertEquals(userDto.country(), result.country());
    }

    @Test
    void givenEmailDoesNotExist_whenSearchingUserByEmail_thenThrowException() {
        //given
        String email = "test@test.com";
        User user = UserFactory.createUser().email(email).build();

        //when
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        //then
        assertThrows(InvalidLoginCredentialsException.class, () -> userService.findUserByEmail(email));
    }

    @Test
    void givenLogInCredentialsNOTValid_whenLoggingIn_thenThrowException() {
        //given
        LoginDto inValidLogin = LoginDto.builder().email("valid@mail.com").password("invalidPassword").build();
        User user = UserFactory.createUser().email("valid@mail.com").password("validPassword").build();
        UserDto userDto = UserDtoFactory.createUserDto().email("valid@mail.com").password("validPassword").build();
        String jwtResponse = "iAmAJwtToken";

        //when
        when(userRepository.findByEmail(inValidLogin.email())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(false);

        //then
        assertThrows(InvalidLoginCredentialsException.class, ()-> userService.login(inValidLogin));
    }

    @Test
    void givenLogInCredentialsAreValid_whenLoggingIn_thenReturnJwtString() {
        //given
        LoginDto validLogin = LoginDto.builder().email("valid@mail.com").password("validPassword").build();
        User user = UserFactory.createUser().email("valid@mail.com").password("validPassword").build();
        UserDto userDto = UserDtoFactory.createUserDto().email("valid@mail.com").password("validPassword").build();
        String jwtResponse = "iAmAJwtToken";

        //when
        when(userRepository.findByEmail(validLogin.email())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(true);
        when(jwtService.generateToken(validLogin.email())).thenReturn(jwtResponse);

        //then
        String result = userService.login(validLogin);
        assertEquals(jwtResponse, result);
    }

}