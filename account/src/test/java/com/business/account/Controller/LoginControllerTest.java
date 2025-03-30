package com.business.account.Controller;

import com.business.account.Entity.UserLogin;
import com.business.account.Repository.LoginRepository;
import com.business.account.Services.UserLoginService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private UserLoginService userLoginService;

    @Test
    void saveUsersLogin() {

        UserLogin userDetails = new UserLogin();
        userDetails.setUserName("Ryan Barcebal");
        userDetails.setPassword("12345");

        Optional<UserLogin> actualCreds = Optional.of(new UserLogin());

        assertTrue(true);
        assertEquals(userDetails.getUserId(), actualCreds.get().getUserId());
        assertEquals(userDetails.getUserName(), actualCreds.get().getUserName());
        assertEquals(userDetails.getPassword(), actualCreds.get().getPassword());

    }


    @Test
    void UserIdNotExisted() {
        UserLogin userDetails = new UserLogin();
        Optional<UserLogin> userId = loginRepository.findById(userDetails.getUserId());
        assertTrue(userId.isEmpty());
    }
}