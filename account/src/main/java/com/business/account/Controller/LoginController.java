package com.business.account.Controller;

import com.business.account.Entity.UserLogin;
import com.business.account.ExceptionHandler.GlobalExceptionHandler;
import com.business.account.Services.UserLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/signin")
    public ResponseEntity<UserLogin> saveUsersLogin(@Valid @RequestBody UserLogin userLogin) {
        UserLogin saveAllUsers = userLoginService.save(userLogin);
        return new ResponseEntity<>(saveAllUsers, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserLogin> getUsersById(@Valid @PathVariable(value = "userId") long userId) throws GlobalExceptionHandler.ErrorResponse {
        UserLogin obj = userLoginService.getUserById(userId);

        UserLogin userLogin = new UserLogin();
        if (obj.getUserId() <= 0) {
//            throw new ResourceNotFoundException("Details of the users not found");
            throw new GlobalExceptionHandler.ErrorResponse(HttpStatus.NOT_FOUND, "User Information not found");
        }else {
            return new ResponseEntity<>(userLogin, HttpStatus.OK);
        }
    }
}
