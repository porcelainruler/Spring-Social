package com.shubham.project.spring_network.persistence.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "Admin")
@DiscriminatorValue(value = "A")
public class Admin extends User {

    public Admin() {
    }

    public Admin(String type, String username, String name, String email, String phone, String address, boolean enabled) {
        super(type, username, name, email, phone, address, enabled);
    }
}
