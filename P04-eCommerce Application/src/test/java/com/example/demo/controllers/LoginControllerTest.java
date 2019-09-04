package com.example.demo.controllers;

import com.example.demo.model.persistence.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class LoginControllerTest {

    private LoginController loginController;
    private User user;

    @Before
    public void setUp() {
        loginController = new LoginController();
        user = new User();
        user.setUsername("test user");
        user.setPassword("testPassword");
    }

    @Test
    public void loginWithValidCredentials() {
        ResponseEntity res = loginController.login(user);
        System.out.println(res);
    }
}
