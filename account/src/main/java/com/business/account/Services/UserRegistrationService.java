package com.business.account.Services;

import com.business.account.Entity.UserRegistration;

import java.util.List;

public interface UserRegistrationService {

    UserRegistration saveUserRegistration(UserRegistration userRegistration);
    UserRegistration getUserRegById(long regId);
    List<UserRegistration> getAllUserRegistration();
}
