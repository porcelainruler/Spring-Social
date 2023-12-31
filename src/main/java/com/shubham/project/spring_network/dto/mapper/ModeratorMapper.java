package com.shubham.project.spring_network.dto.mapper;

import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import com.shubham.project.spring_network.persistence.model.Moderator;
import com.shubham.project.spring_network.persistence.model.Privilege;
import com.shubham.project.spring_network.persistence.model.Role;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;

public class ModeratorMapper implements IDTOMapper<Moderator, ModeratorDTO> {

    @Override
    public Moderator toEntity(ModeratorDTO dto) throws Exception {
        if (dto == null) {
            throw new Exception("Invalid input parameter dto, dto object passed must be an instance of " + ModeratorDTO.class);
        }
        return null;
    }

    @Override
    public ModeratorDTO toDTO(Moderator moderator) throws Exception {
        if (moderator == null) {
            throw new Exception("Invalid input object moderator, moderator object passed must not be null and an instance of " + Moderator.class);
        }

        ModeratorDTO moderatorDTO = ModeratorDTO.builder()
                .id(moderator.getId())
                .name(moderator.getName())
                .username(moderator.getUsername())
                .email(moderator.getEmail())
                .phone(moderator.getPhone())
                .address(moderator.getAddress())
                .enabled(moderator.isEnabled())
                .accountStatus(moderator.getAccount().getStatus().toString())
                .accountPlatform(moderator.getAccount().getPlatform().toString())
                .roles(moderator.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .privileges(moderator.getRoles().stream().flatMap(role-> role.getPrivileges().stream().map(Privilege::getName)).collect(Collectors.toList()))
                .build();

        return moderatorDTO;
    }
}
