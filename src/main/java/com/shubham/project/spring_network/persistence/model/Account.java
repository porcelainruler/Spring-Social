package com.shubham.project.spring_network.persistence.model;

import com.shubham.project.spring_network.constant.AccountStatus;
import com.shubham.project.spring_network.constant.Platform;
import com.shubham.project.spring_network.constant.UserType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    private Platform platform;

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "userType")
    private UserType userType;

    public Account () {

    }

    public Account(Platform platform, String username, String email, String phone, AccountStatus status, UserType userType) {
        this.platform = platform;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.userType = userType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Account account = (Account) o;
//        return id == account.id && Objects.equals(platform, account.platform) && Objects.equals(username, account.username) && Objects.equals(email, account.email) && Objects.equals(phone, account.phone);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, platform, username, email, phone);
//    }
}
