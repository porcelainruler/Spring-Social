package com.shubham.project.spring_network.jobs;

import com.shubham.project.spring_network.constant.*;
import com.shubham.project.spring_network.persistence.dao.*;
import com.shubham.project.spring_network.persistence.model.*;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private AccountDAO accountDAO;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private ReactionDAO reactionDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private ReplyDAO replyDAO;

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

        // * Testing code
        List<Post> fetchPost = postDAO.findAll();
        System.out.println(fetchPost);

        Member mFetch1 = memberDAO.findById(1);
        //        System.out.println(mFetch1.getReactions().size());
        System.out.println(mFetch1);
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

            // * Account setup
            Account userAccount = new Account(Platform.SPRING_SOCIAL, "user1", "shubham@springsocial.com", "(+91) 7011012392", AccountStatus.ENABLED, UserType.MEMBER);
            Account moderatorAccount = new Account(Platform.SPRING_SOCIAL, "moderator1", "mohan@springsocial.com", "(+91) 9912345643", AccountStatus.ENABLED, UserType.MODERATOR);
            Account adminAccount = new Account(Platform.SPRING_SOCIAL, "admin1", "alex@springsocial.com", "(+91) 5467112321", AccountStatus.ENABLED, UserType.ADMIN);

            if ( accountDAO.findByEmail("shubham@springsocial.com") == null ) accountDAO.save(userAccount);
            if ( accountDAO.findByEmail("mohan@springsocial.com") == null ) accountDAO.save(moderatorAccount);
            if ( accountDAO.findByEmail("alex@springsocial.com") == null ) accountDAO.save(adminAccount);

            Member m1 = new Member("U", "user1", passwordEncoder.encode("test123"), "shubham", "shubham@springsocial.com", "(+91) 7011012392", "NA", true, memberRoles, userAccount, null, null);
            Moderator m2 = new Moderator("M", "moderator1", passwordEncoder.encode("test123"), "mohan", "mohan@springsocial.com", "(+91) 9912345643", "NA", true, moderatorRoles, moderatorAccount);
            Admin m3 = new Admin("A", "admin1", passwordEncoder.encode("test123"), "alex", "alex@springsocial.com", "(+91) 5467112321", "NA", true, adminRoles, adminAccount);

            if ( memberDAO.findByEmail("shubham@springsocial.com") == null ) memberDAO.save(m1);
            if ( moderatorDAO.findByEmail("mohan@springsocial.com") == null ) moderatorDAO.save(m2);
            if ( adminDAO.findByEmail("alex@springsocial.com") == null ) adminDAO.save(m3);

            // * Create post for member
            if (postDAO.findById((long)1) == null) createPostForUser(m1);

            // Post post = postDAO.findById((long)1);
            // System.out.println(post);
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

    @Transactional
    private void createPostForUser (User user) {
        Post temp = postDAO.findById((long)1);
        if (temp == null) {
            Post post = new Post(user, "New post for testing", new Date(), new Date());

            postDAO.save(post);

            Reaction reaction1 = new Reaction(user, post, Rating.LIKE, null, null, ReactionType.POST);
            Reaction reaction2 = new Reaction(user, post, Rating.LIKE, null, null, ReactionType.POST);
            Reaction reaction3 = new Reaction(user, post, Rating.CRY, null, null, ReactionType.POST);
            Reaction reaction4 = new Reaction(user, post, Rating.ANGRY, null, null, ReactionType.POST);
            Reaction reaction5 = new Reaction(user, post, Rating.HEART, null, null, ReactionType.POST);
            Reaction reaction6 = new Reaction(user, post, Rating.LAUGH, null, null, ReactionType.POST);
            Reaction reaction7 = new Reaction(user, post, Rating.SUPPORT, null, null, ReactionType.POST);

            reactionDAO.save(reaction1);
            reactionDAO.save(reaction2);
            reactionDAO.save(reaction3);
            reactionDAO.save(reaction4);
            reactionDAO.save(reaction5);
            reactionDAO.save(reaction6);
            reactionDAO.save(reaction7);

            createCommentReplyForPost(user, post, "NA");
        }
    }

    @Transactional
    private void createCommentReplyForPost (User user, Post post, String message) {
        Comment com1 = new Comment(user, post, "Looks like a good post", null, null);
        Comment com2 = new Comment(user, post, "Finally a +ve post", null, null);

        commentDAO.save(com1);
        commentDAO.save(com2);

        Reaction react1 = new Reaction(user, null, Rating.LIKE, com1, null, ReactionType.COMMENT);
        Reaction react2 = new Reaction(user, null, Rating.HEART, com1, null, ReactionType.COMMENT);
        Reaction react3 = new Reaction(user, null, Rating.SUPPORT, com2, null, ReactionType.COMMENT);
        Reaction react4 = new Reaction(user, null, Rating.LIKE, com2, null, ReactionType.COMMENT);

        reactionDAO.save(react1);
        reactionDAO.save(react2);
        reactionDAO.save(react3);
        reactionDAO.save(react4);

        Set<Reaction> rSet1 = new HashSet<>(), rSet2 = new HashSet<>();
        rSet1.add(react1);
        rSet1.add(react2);
        rSet2.add(react3);
        rSet2.add(react4);

        com1.setReactions(rSet1);
        com2.setReactions(rSet2);

        commentDAO.save(com1);
        commentDAO.save(com2);

        Set<Comment> cSet = new HashSet<>();
        cSet.add(com1);
        cSet.add(com2);
        post.setComments(cSet);
        postDAO.save(post);
    }
}

