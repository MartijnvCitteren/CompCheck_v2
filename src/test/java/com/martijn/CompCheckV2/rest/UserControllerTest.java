package com.martijn.CompCheckV2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.martijn.CompCheckV2.rest.dto.UserDto;
import com.martijn.CompCheckV2.rest.dto.UserDtoFactory;
import com.martijn.CompCheckV2.rest.dto.requests.UserRegisterRequest;
import com.martijn.CompCheckV2.rest.dto.requests.UserRegistrationRequestFactory;
import com.martijn.CompCheckV2.rest.dto.response.UserRegisterResponse;
import com.martijn.CompCheckV2.rest.dto.response.UserRegistrationResponseFactory;
import com.martijn.CompCheckV2.rest.mapper.UserMapper;
import com.martijn.CompCheckV2.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    private final String registerUri = "/api/compcheck/user/register";
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    private ObjectWriter jsonWriter;
    private UserRegisterRequest registerRequest;
    private UserDto userDto;


    @BeforeEach
    void setUp() {
        jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        userDto = UserDtoFactory.createUserDto().build();
    }

    @Test
    void givenValidRegistrationRequest_whenRegisteringNewUser_thenShouldReturnUserAndStatus201() throws Exception {
        //given
        registerRequest = UserRegistrationRequestFactory.userRegisterRequest().build();
        UserRegisterResponse expectedResponse = UserRegistrationResponseFactory.userRegisterResponse().build();

        //when
        when(userService.registerUser(UserMapper.requestToDto(registerRequest))).thenReturn(userDto);

        //then
        mockMvc.perform(post(registerUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonWriter.writeValueAsString(registerRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(expectedResponse.firstName()))
                .andExpect(jsonPath("$.lastName").value(expectedResponse.lastName()))
                .andExpect(jsonPath("$.email").value(expectedResponse.email()))
                .andExpect(jsonPath("$.city").value(expectedResponse.city()))
                .andExpect(jsonPath("$.country").value(expectedResponse.country()));
    }

    @Test
    void givenFirstNameIsBlankInRequest_whenRegisteringNewUser_thenShouldReturnStatus400() throws Exception {
        //given
        registerRequest = UserRegistrationRequestFactory.userRegisterRequest().firstName("").build();

        //when & then
        mockMvc.perform(post(registerUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonWriter.writeValueAsString(registerRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName").value("must not be blank"));
    }

    @Test
    void givenEmailFormatIsWrongInRequest_whenRegisteringNewUser_thenShouldReturnStatus400() throws Exception {
        //given
        registerRequest = UserRegistrationRequestFactory.userRegisterRequest().email("wronggmail.com").build();

        //when & then
        mockMvc.perform(post(registerUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonWriter.writeValueAsString(registerRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").value("must be a well-formed email address"));
    }
}