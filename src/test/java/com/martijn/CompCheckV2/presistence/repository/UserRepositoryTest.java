package com.martijn.CompCheckV2.presistence.repository;

import com.martijn.CompCheckV2.presistence.entity.User;
import com.martijn.CompCheckV2.presistence.entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = UserFactory.createUser().build();
    }

    @Test
    void givenUserEmailExists_existsByEmail_thenReturnTrue() {
        //given
        userRepository.save(user);

        //when & then
        assertTrue(userRepository.existsByEmail(user.getEmail()));
    }
}