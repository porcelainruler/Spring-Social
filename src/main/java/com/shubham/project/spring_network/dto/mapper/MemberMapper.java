package com.shubham.project.spring_network.dto.mapper;

import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.persistence.model.Member;
import com.shubham.project.spring_network.persistence.model.Privilege;
import com.shubham.project.spring_network.persistence.model.Role;

import java.util.stream.Collectors;

public class MemberMapper implements IDTOMapper {
    @Override
    public Object toEntity(Object dto) throws Exception {
        return null;
    }

    @Override
    public Object toDTO(Object entity) throws Exception {
        if (!(entity instanceof Member)) {
            throw new Exception("Invalid input parameter dto, dto object passed must be an instance of " + Member.class);
        }
        Member member = (Member) entity;

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
