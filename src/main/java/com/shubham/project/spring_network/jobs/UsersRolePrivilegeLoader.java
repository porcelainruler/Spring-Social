package com.shubham.project.spring_network.jobs;

import com.shubham.project.spring_network.persistence.dao.*;
import com.shubham.project.spring_network.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        try {
            Collection<Role> memberRoles = new ArrayList<>();
            Collection<Role> moderatorRoles = new ArrayList<>();
            Collection<Role> adminRoles = new ArrayList<>();

            Collection<Privilege> memberPrivilege = new ArrayList<>();
            Collection<Privilege> moderatorPrivilege = new ArrayList<>();
            Collection<Privilege> adminPrivilege = new ArrayList<>();

            // * Privilege DB persist logic
            Privilege readPriv = privilegeDAO.findByName("READ");
            if (readPriv == null) {
                readPriv = new Privilege("READ");
                privilegeDAO.save(readPriv);
            }
            Privilege writePriv = privilegeDAO.findByName("WRITE");
            if (writePriv == null) {
                writePriv = new Privilege("WRITE");
                privilegeDAO.save(writePriv);
            }
            Privilege deletePriv = privilegeDAO.findByName("DELETE");
            if (deletePriv == null) {
                deletePriv = new Privilege("DELETE");
                privilegeDAO.save(deletePriv);
            }
            memberPrivilege.add(readPriv);
            moderatorPrivilege.add(readPriv);
            moderatorPrivilege.add(writePriv);
            adminPrivilege.add(readPriv);
            adminPrivilege.add(writePriv);
            adminPrivilege.add(deletePriv);

            // * Role DB persist logic
            Role memberRole = roleDAO.findByName("ROLE_MEMBER");
            if (memberRole == null) {
                memberRole = new Role("ROLE_MEMBER", memberPrivilege);
                roleDAO.save(memberRole);
            }
            Role moderatorRole = roleDAO.findByName("ROLE_MODERATOR");
            if (moderatorRole == null) {
                moderatorRole = new Role("ROLE_MODERATOR", moderatorPrivilege);
                roleDAO.save(moderatorRole);
            }
            Role adminRole = roleDAO.findByName("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = new Role("ROLE_ADMIN", adminPrivilege);
                roleDAO.save(adminRole);
            }

            memberRoles.add(memberRole);
            moderatorRoles.add(moderatorRole);
            adminRoles.add(adminRole);

            Member m1 = new Member("U", "user1", passwordEncoder.encode("test123"), "shubham", "shubham@springsocial.com", "(+91) 7011012392", "NA", true, memberRoles);
            Moderator m2 = new Moderator("M", "moderator1", passwordEncoder.encode("test123"), "mohan", "mohan@springsocial.com", "(+91) 9912345643", "NA", true, moderatorRoles);
            Admin m3 = new Admin("A", "admin1", passwordEncoder.encode("test123"), "alex", "alex@springsocial.com", "(+91) 5467112321", "NA", true, adminRoles);

            if ( memberDAO.findByEmail("shubham@springsocial.com") == null ) memberDAO.save(m1);
            if ( moderatorDAO.findByEmail("mohan@springsocial.com") == null ) moderatorDAO.save(m2);
            if ( adminDAO.findByEmail("alex@springsocial.com") == null ) adminDAO.save(m3);
            //        List<Member> members = memberDAO.findAll();
            //
            //        for (Member member: members) {
            //            Collection<Role> roles = member.getRoles();
            //
            //            for (Role role : roles) {
            //                 // TODO
            //            }
            //        }
        } catch (Exception e) {
            e.printStackTrace();
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

