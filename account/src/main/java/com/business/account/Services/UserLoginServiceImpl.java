package com.business.account.Services;

import com.business.account.Entity.UserLogin;
import com.business.account.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    private LoginRepository loginRepo;

    @Override
    public UserLogin save(UserLogin userLogin) {
        loginRepo.save(userLogin);
        return userLogin;
    }

    @Override
    public UserLogin getUserById(long id) {
        Optional<UserLogin> checkId = loginRepo.findById(id);
        UserLogin userLogin = new UserLogin();

        if(checkId.isEmpty()) {
            throw new RuntimeException("login info with the id of, " + id + "not found");
        }else {
                return userLogin;
        }
    }
    
    

    @Override
    public List<UserLogin> getAllUser() {
        return loginRepo.findAll();
    }
}
