package com.shubham.project.spring_network.security;

import com.shubham.project.spring_network.persistence.dao.*;
import com.shubham.project.spring_network.persistence.model.Privilege;
import com.shubham.project.spring_network.persistence.model.Role;
import com.shubham.project.spring_network.persistence.model.User;
import com.shubham.project.spring_network.service.AdminService;
import com.shubham.project.spring_network.service.MemberService;
import com.shubham.project.spring_network.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private ModeratorDAO moderatorDAO;
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private MemberService memberSvc;
    @Autowired
    private ModeratorService moderatorSvc;
    @Autowired
    private AdminService adminSvc;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;

        user = memberDAO.findByEmail(email);

        if (user == null) {
            // Check for moderator
            user = moderatorDAO.findByEmail(email);

            if (user == null) {
                // Check for admin
                user = adminDAO.findByEmail(email);
            }
        }

        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(Arrays.asList(
                            roleDAO.findByName("ROLE_USER"))));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(user.getRoles()));
    }

    private Collection< ? extends GrantedAuthority > getAuthorities (final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    // Return a list of string with all privilege strings
    private List<String> getPrivileges(final Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();

        // Loop through roles and add role name to privilege | Also add role privileges to collection to be used later
        // to add them as well in privilege list
        for (Role role: roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }

        for (Privilege privilege : collection) {
            privileges.add(privilege.getName());
        }

        return privileges;
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();

        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
