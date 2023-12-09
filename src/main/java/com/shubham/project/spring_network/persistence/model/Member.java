package com.shubham.project.spring_network.persistence.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Collection;
import java.util.Set;

@Entity()
@DiscriminatorValue(value = "U")
public class Member extends User {

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    @OneToMany(mappedBy = "user")
    private Set<Reaction> reactions;

    public Member() {
        super();
    }

    public Member(String type, String username, String password, String name, String email, String phone, String address, boolean enabled, Collection<Role> roles, Account account) {
        super(type, username, password, name, email, phone, address, enabled, roles, account);
    }
}
