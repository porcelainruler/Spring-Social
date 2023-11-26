package com.shubham.project.spring_network.persistence.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "Member")
@DiscriminatorValue(value = "U")
public class Member extends User {

    public Member() {
        super();
    }

    public Member(String type, String username, String name, String email, String phone, String address, boolean enabled) {
        super(type, username, name, email, phone, address, enabled);
    }
}
