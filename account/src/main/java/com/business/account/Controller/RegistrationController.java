package com.business.account.Controller;

import com.business.account.Entity.UserRegistration;
import com.business.account.ExceptionHandler.ResourceNotFoundException;
import com.business.account.Services.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Registration")
public class RegistrationController {

    @Autowired
    private UserRegistrationService userRegService;

    @PostMapping("/new")
    public ResponseEntity<UserRegistration> saveUserReg (@RequestBody UserRegistration userReg) {
        UserRegistration saveAllUser = userRegService.saveUserRegistration(userReg);
        return new ResponseEntity<>(saveAllUser, HttpStatus.CREATED);
    }

    @GetMapping("/newUser/{regId}")
    public ResponseEntity<UserRegistration> getUserRegById (@Valid @PathVariable(value = "regId") Long regId) throws ResourceNotFoundException {
        UserRegistration getAllUserRegById = userRegService.getUserRegById(regId);
        return new ResponseEntity<>(getAllUserRegById, HttpStatus.OK);
    }

    @GetMapping("/newUser")
    public ResponseEntity<List<UserRegistration>> getAllUserReg () {
        List<UserRegistration> getAllUser = userRegService.getAllUserRegistration();
        return new ResponseEntity<>(getAllUser, HttpStatus.OK);
    }
}
