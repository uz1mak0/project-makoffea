package com.business.account.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Registration")
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regID")
    private long regId;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Address")
    private String Address;

    @Column(name = "Email")
    private String Email;

    @Column(name = "ContactNumber")
    private int ContactNumber;

    public UserRegistration() {}


    public UserRegistration(long regId, String name, String address, String email, int contactNumber) {
        this.regId = regId;
        Name = name;
        Address = address;
        Email = email;
        ContactNumber = contactNumber;
    }


    public long getRegId() {
        return regId;
    }

    public void setRegId(long regId) {
        this.regId = regId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(int contactNumber) {
        ContactNumber = contactNumber;
    }
}
