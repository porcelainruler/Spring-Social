package com.shubham.project.spring_network.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "Member")
@DiscriminatorValue(value = "U")
public class Member extends User {

    public Member(String type, String username, String name, String email, String phone, String address) {
        super(type, username, name, email, phone, address);
    }
}
