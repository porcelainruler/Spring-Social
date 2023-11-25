package com.shubham.project.spring_network.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "Admin")
@DiscriminatorValue(value = "A")
public class Admin extends User {

    public Admin(String type, String username, String name, String email, String phone, String address) {
        super(type, username, name, email, phone, address);
    }
}
