package com.business.account.Services;

import com.business.account.Entity.UserRegistration;
import com.business.account.ExceptionHandler.ResourceNotFoundException;
import com.business.account.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{

    @Autowired
    private RegistrationRepository regRepo;


    @Override
    public UserRegistration saveUserRegistration(UserRegistration userRegistration) {

        return regRepo.save(userRegistration);
    }

    @Override
    public UserRegistration getUserRegById(long regId) {

        Optional<UserRegistration> getById = regRepo.findById(regId);

        UserRegistration userRegistration = new UserRegistration();

        if (getById.isEmpty()) {
            throw new ResourceNotFoundException("user registration with the id of " + regId + "not found");
        }else {
            return userRegistration;
        }
    }

    @Override
    public List<UserRegistration> getAllUserRegistration() {

        return regRepo.findAll();
    }
}
