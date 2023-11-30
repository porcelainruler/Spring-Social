package com.shubham.project.spring_network.security;

import com.shubham.project.spring_network.persistence.dao.*;
import com.shubham.project.spring_network.persistence.model.Privilege;
import com.shubham.project.spring_network.persistence.model.Role;
import com.shubham.project.spring_network.persistence.model.User;
import com.shubham.project.spring_network.service.AdminService;
import com.shubham.project.spring_network.service.MemberService;
import com.shubham.project.spring_network.service.ModeratorService;
import com.shubham.project.spring_network.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service(value = "userDetailsService")
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;

        user = memberDAO.findByUsername(username);

        if (user == null) {
            // Check for moderator
            user = moderatorDAO.findByUsername(username);

            if (user == null) {
                // Check for admin
                user = adminDAO.findByUsername(username);
            }
        }

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username / password");
        }

        return UserDetailsImpl.build(user);
    }

}
