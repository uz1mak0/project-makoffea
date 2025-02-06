package com.business.account.Entity;

import com.business.account.ExceptionHandler.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Login")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private long userId;

    @Column(name = "UserName")
    @NotBlank(message = "Name field cannot be blank!")
    @Size(min = 4, max = 50, message = "Name must be in between 4 and 50 char")
    private String userName;

    @Column(name = "Password")
    @NotBlank(message = "Password must not be empty!")
    @ValidPassword(message = "Password must be valid and suits the policy requirements!")
    private String password;

    public UserLogin() {
    }

    public UserLogin(long userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
