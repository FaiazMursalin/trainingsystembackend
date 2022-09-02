package com.example.trainingsystembackend;


import com.example.trainingsystembackend.model.User;
import com.example.trainingsystembackend.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepoTest {
    @Before
    public void setup()
    {
        System.out.println("....Unit Test Started....");
    }

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testRetrieveStudentWithMockRepository1() throws Exception {

        Optional<User> optStudent = Optional.of( new User("admin","$2a$12$nP9RsME7AsGomq9Hs4b2zePpJFdeo8J6QbNzNcBBFB3Za29u93g3G","ADMIN"));
        when(userRepository.findById(1L)).thenReturn(optStudent);

        assertTrue(userRepository.findById(1L).get().getUsername().contains("admin"));
    }

    @Test
    public void testRetrieveStudentWithMockRepository2() throws Exception {

        Optional<User> optStudent = Optional.of( new User("admin","$2a$12$nP9RsME7AsGomq9Hs4b2zePpJFdeo8J6QbNzNcBBFB3Za29u93g3G","ADMIN"));
        when(userRepository.findById(1L)).thenReturn(optStudent);

        assertTrue(userRepository.findById(1L).get().getUsername().contains("anyothername"));
    }




    @After
    public void tearDown()
    {
        System.out.println("....Unit Test Ended....");
    }

}
