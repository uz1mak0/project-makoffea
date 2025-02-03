package com.business.account.Services;

import com.business.account.Entity.UserLogin;

import java.util.List;

public interface UserLoginService {

    UserLogin save(UserLogin userLogin);
    UserLogin getUserById(long id);
    List<UserLogin> getAllUser();
}
