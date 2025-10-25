package com.blogger.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogger.entity.UserEntity;
import com.blogger.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    // @BeforeEach // runs before each test case
    // public void setup() {
    // }

    // @Disabled //disable this test
    @Test
    public void test() {
        // assertEquals(2, 1 + 1);
        UserEntity user = userRepository.findByUsername("test").orElse(null);
        // assertNotNull(user);
        // assertTrue(user.getBlogs().size() >= 0);
    }


    @ParameterizedTest
    @ValueSource(strings ={
        "test",
        "admin",
        "user123"
    })
    public void test(String name) {
        // assertEquals(2, 1 + 1);
        UserEntity user = userRepository.findByUsername(name).orElse(null);
        assertNotNull(user, "User not found for username: " + name);
        // assertTrue(user.getBlogs().size() >= 0);
    }

    //parameterized test for addUser
    @ParameterizedTest
    @CsvSource({
        "1, 1, 2",
        "2, 3, 5",
        "10, 20, 40"
    })
    public void parameterizedTest(int a, int b, int expected) {
        assertEquals(expected, a + b, "Addition result should match expected value! Failed for inputs: " + a + ", " + b);
    }
}
