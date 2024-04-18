package com.martijn.CompCheckV2.rest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.martijn.CompCheckV2.rest.dto.requests.UserRegisterRequest;
import com.martijn.CompCheckV2.rest.dto.requests.UserRegistrationRequestFactory;
import com.martijn.CompCheckV2.rest.dto.response.UserRegisterResponse;
import com.martijn.CompCheckV2.service.UserService;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.http.RequestEntity.post;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    private final String registerUri = "api/compcheck/user/register";
    private ObjectMapper objectMapper;
    private UserRegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper(new JsonFactory());

    }

    @Test
    void givenValidRegistrationRequest_whenRegisteringNewUser_thenShouldReturnUserAndStatus201() throws Exception {
        //given
        registerRequest = UserRegistrationRequestFactory.userRegisterRequest().build();

        //when & then
        mockMvc.perform(post(registerUri)
                .accept(MediaType.APPLICATION_JSON));


    }

}