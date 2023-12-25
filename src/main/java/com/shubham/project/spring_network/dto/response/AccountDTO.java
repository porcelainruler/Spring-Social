package com.shubham.project.spring_network.dto.response;

import com.shubham.project.spring_network.constant.AccountStatus;
import com.shubham.project.spring_network.constant.Platform;
import com.shubham.project.spring_network.constant.UserType;
import lombok.Data;

@Data
public class AccountDTO {

    private long id;

    private Platform platform;

    private String username;

    private AccountStatus status;

    private UserType userType;

    public AccountDTO () {

    }

    public AccountDTO(long id, Platform platform, String username, AccountStatus status, UserType userType) {
        this.id = id;
        this.platform = platform;
        this.username = username;
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

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
