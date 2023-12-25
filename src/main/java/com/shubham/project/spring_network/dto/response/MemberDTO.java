package com.shubham.project.spring_network.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shubham.project.spring_network.constant.Platform;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MemberDTO {
    private long id;

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
