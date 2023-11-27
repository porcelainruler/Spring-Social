package com.shubham.project.spring_network.persistence.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Collection;

@Entity(name = "Admin")
@DiscriminatorValue(value = "A")
public class Admin extends User {

    public Admin() {
    }

    public Admin(String type, String username, String password, String name, String email, String phone, String address, boolean enabled, Collection<Role> roles) {
        super(type, username, password, name, email, phone, address, enabled, roles);
    }
}
