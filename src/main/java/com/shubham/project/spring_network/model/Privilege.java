package com.shubham.project.spring_network.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity(name = "Privilege")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
