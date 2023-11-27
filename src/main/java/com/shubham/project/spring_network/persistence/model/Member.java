package com.shubham.project.spring_network.persistence.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Collection;

@Entity(name = "Member")
@DiscriminatorValue(value = "U")
public class Member extends User {

    public Member() {
        super();
    }

    public Member(String type, String username, String password, String name, String email, String phone, String address, boolean enabled, Collection<Role> roles) {
        super(type, username, password, name, email, phone, address, enabled, roles);
    }
}