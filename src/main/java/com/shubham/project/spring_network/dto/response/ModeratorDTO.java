package com.shubham.project.spring_network.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModeratorDTO {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String address;

    private Boolean enabled;

    private String accountStatus;

    private String accountPlatform;

    private List<String> roles;

    private List<String> privileges;

}

