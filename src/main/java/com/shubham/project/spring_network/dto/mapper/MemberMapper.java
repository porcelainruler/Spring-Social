package com.shubham.project.spring_network.dto.mapper;

import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.persistence.model.Member;
import com.shubham.project.spring_network.persistence.model.Privilege;
import com.shubham.project.spring_network.persistence.model.Role;

import java.util.stream.Collectors;

public class MemberMapper implements IDTOMapper<Member, MemberDTO> {
    @Override
    public Member toEntity(MemberDTO dto) throws Exception {
        return null;
    }

    @Override
    public MemberDTO toDTO(Member member) throws Exception {
        if (member == null) {
            throw new Exception("Invalid input parameter member, member object passed must not be null and an instance of " + Member.class);
        }

        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .username(member.getUsername())
                .email(member.getEmail())
                .phone(member.getPhone())
                .address(member.getAddress())
                .enabled(member.isEnabled())
                .accountStatus(member.getAccount().getStatus().toString())
                .accountPlatform(member.getAccount().getPlatform().toString())
                .roles(member.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .privileges(member.getRoles().stream().flatMap(role-> role.getPrivileges().stream().map(Privilege::getName)).collect(Collectors.toList()))
                .build();

        return memberDTO;
    }
}
