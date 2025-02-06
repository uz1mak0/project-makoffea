package com.business.account.Entity;

import com.business.account.ExceptionHandler.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "Registration")
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regID")
    private long regId;

    @Column(name = "Name")
    @NotBlank(message = "Name field cannot be blank")
    @Size(min = 4, max = 50, message = "Name must be in between 4 and 50 character")
    private String name;

    @Column(name = "userName")
    @NotBlank(message = "Username field cannot be blank")
    private String userName;

    @Column(name = "Address")
    @NotBlank(message = "Address field cannot be blank")
    private String address;

    @Column(name = "Email")
    @NotBlank(message = "Email field cannot be blank")
    private String email;

    @Column(name = "ContactNumber")
    @NotBlank(message = "Contact field cannot be blank")
    private int contactNumber;

    @Column(name = "Password")
    @NotBlank(message = "Password field cannot be blank")
    @ValidPassword(message = "Password not valid!")
    private String password;


    public UserRegistration() {}


    public UserRegistration(long regId, String name, String userName, String address, String email, int contactNumber, String password) {
        this.regId = regId;
        this.name = name;
        this.userName = userName;
        this.address = address;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
    }

    public long getRegId() {
        return regId;
    }

    public void setRegId(long regId) {
        this.regId = regId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
