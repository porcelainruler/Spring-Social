package com.shubham.project.spring_network.persistence.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Collection;

@Entity(name = "Moderator")
@DiscriminatorValue(value = "M")
public class Moderator extends User {

    public Moderator() {
    }

    public Moderator(String type, String username, String password, String name, String email, String phone, String address, boolean enabled, Collection<Role> roles) {
        super(type, username, password, name, email, phone, address, enabled, roles);
    }
}