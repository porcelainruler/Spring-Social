package com.shubham.project.spring_network.persistence.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "Moderator")
@DiscriminatorValue(value = "M")
public class Moderator extends User {

    public Moderator() {
    }

    public Moderator(String type, String username, String name, String email, String phone, String address, boolean enabled) {
        super(type, username, name, email, phone, address, enabled);
    }
}
