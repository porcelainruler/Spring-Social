package com.shubham.project.spring_network.service;

import com.shubham.project.spring_network.constant.AccountStatus;
import com.shubham.project.spring_network.constant.Platform;
import com.shubham.project.spring_network.constant.UserType;
import com.shubham.project.spring_network.dto.mapper.IDTOMapper;
import com.shubham.project.spring_network.dto.mapper.MemberMapper;
import com.shubham.project.spring_network.dto.request.MemberCreateDTO;
import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.exceptions.UserNotFoundException;
import com.shubham.project.spring_network.exceptions.ValidationException;
import com.shubham.project.spring_network.persistence.dao.AccountDAO;
import com.shubham.project.spring_network.persistence.dao.MemberDAO;
import com.shubham.project.spring_network.persistence.dao.RoleDAO;
import com.shubham.project.spring_network.persistence.model.Account;
import com.shubham.project.spring_network.persistence.model.Member;
import com.shubham.project.spring_network.persistence.model.Privilege;
import com.shubham.project.spring_network.persistence.model.Role;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class MemberService implements IMemberService {
    // * Logger
    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    // * DAOs
    private final MemberDAO memberDAO;

    private final RoleDAO roleDAO;

    private final AccountDAO accountDAO;

    // * Model mapper
    private final IDTOMapper<Member, MemberDTO> modelMapperCustom;

    // * Password encoder
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public MemberService (MemberDAO memberDAO,
                          RoleDAO roleDAO,
                          AccountDAO accountDAO,
                          PasswordEncoder passwordEncoder) {
        this.memberDAO = memberDAO;
        this.roleDAO = roleDAO;
        this.accountDAO = accountDAO;
        this.modelMapperCustom = new MemberMapper();
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<MemberDTO> findAllDTO() {
        return memberDAO.findAllDTO();
    }

    @Override
    public MemberDTO findDTOById(long id) throws Exception {
        return memberDAO.findDTOById(id);
    }

    @Override
    public MemberDTO createMember(MemberCreateDTO memberDTO) throws Exception {
        Collection<Role> roles = new ArrayList<>();

        // Check if role exists and create role mapping for user
        for (String roleName : memberDTO.getRoles()) {
            Role role = roleDAO.findByName(roleName);

            if (role == null) {
                throw new ValidationException("Validation error: No role exists with name " + roleName);
            }

            roles.add(role);
        }

        // Check account if exists raise error, else create account and persist in db
        Account account = accountDAO.findByEmail(memberDTO.getEmail());
        if (account != null) {
            throw new ValidationException("Validation error: Another user is registered with provided email " + memberDTO.getEmail());
        }
        account = new Account(Platform.SPRING_SOCIAL, memberDTO.getUsername(), memberDTO.getEmail(), memberDTO.getPhone(), AccountStatus.ENABLED, UserType.MEMBER);
        accountDAO.save(account);

        // Create member and persist in db
        Member member = new Member("U", memberDTO.getUsername(), passwordEncoder.encode(memberDTO.getPassword()), memberDTO.getName(), memberDTO.getEmail(), memberDTO.getPhone(), memberDTO.getAddress(), true, roles, account, null, null);
        memberDAO.save(member);

        return this.modelMapperCustom.toDTO(member);
    }

    @Override
    public MemberDTO updateMember(MemberCreateDTO memberDTO) throws Exception {

        Member existMember = memberDAO.findByEmail(memberDTO.getEmail());

        if (existMember == null) {
            throw new UserNotFoundException("No member exist with email " + memberDTO.getEmail());
        }

        // Update user basic details as per request body | Only those fields whose update is supported
        existMember.setName(memberDTO.getName());
        existMember.setUsername(memberDTO.getUsername());
        existMember.setUpdatedAt(new Date());
        existMember.setAddress(memberDTO.getAddress());
        existMember.setEnabled(memberDTO.isEnabled());

        // Update account status - Not supported yet

        // Persist in db
        memberDAO.save(existMember);

        return modelMapperCustom.toDTO(existMember);
    }

    @Override
    public boolean deleteMember(long id) throws UserNotFoundException {
        Member member = memberDAO.findById(id);

        if (member == null) {
            throw new UserNotFoundException("No member exist with id " + id);
        }

        // TODO: Add other mappings cleanup logic

        memberDAO.delete(member);

        return true;
    }
}
