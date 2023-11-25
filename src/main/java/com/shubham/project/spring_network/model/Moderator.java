package com.shubham.project.spring_network.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "Moderator")
@DiscriminatorValue(value = "M")
public class Moderator extends User {

    public Moderator(String type, String username, String name, String email, String phone, String address) {
        super(type, username, name, email, phone, address);
    }
}
