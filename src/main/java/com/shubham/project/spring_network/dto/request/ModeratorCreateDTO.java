package com.shubham.project.spring_network.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ModeratorCreateDTO {

    private String name;

    private String username;

    private String email;

    private String phone;

    private String address;

    private boolean enabled;

    private String accountStatus;

    private String accountPlatform;

    private List<String> roles;

    private List<String> privileges;
}
