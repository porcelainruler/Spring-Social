package com.shubham.project.spring_network.jobs;

import com.shubham.project.spring_network.persistence.dao.*;
import com.shubham.project.spring_network.persistence.model.Admin;
import com.shubham.project.spring_network.persistence.model.Member;
import com.shubham.project.spring_network.persistence.model.Moderator;
import com.shubham.project.spring_network.persistence.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Component
public class UsersRolePrivilegeLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean isJobCompleted = false;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private ModeratorDAO moderatorDAO;

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PrivilegeDAO privilegeDAO;

    //    @Autowired
    //    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (isJobCompleted)
            return;

        loadAllMembers();
        loadAllModerators();
        loadAllAdmins();
    }

    @Transactional
    private void loadAllMembers () {
        List<Member> members = memberDAO.findAll();

        for (Member member: members) {
            Collection<Role> roles = member.getRoles();

            for (Role role : roles) {
                 // TODO
            }
        }
    }

    @Transactional
    private void loadAllModerators () {
        List<Moderator> moderators = moderatorDAO.findAll();

    }

    @Transactional
    private void loadAllAdmins () {
        List<Admin> admins = adminDAO.findAll();

    }
}

