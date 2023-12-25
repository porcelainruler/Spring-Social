package com.shubham.project.spring_network.persistence.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubham.project.spring_network.config.ModelMapperConfig;
import com.shubham.project.spring_network.dto.mapper.IDTOMapper;
import com.shubham.project.spring_network.dto.mapper.ModeratorMapper;
import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import com.shubham.project.spring_network.persistence.model.Moderator;
import com.shubham.project.spring_network.persistence.model.Privilege;
import com.shubham.project.spring_network.persistence.model.Role;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ModeratorDAOImpl implements ModeratorDAOCustom {

    private final ModeratorDAO moderatorDAO;

    private final IDTOMapper modelMapperCustom;

    @Autowired
    @Lazy
    public ModeratorDAOImpl(ModeratorDAO moderatorDAO, ModeratorMapper moderatorMapper) {
        this.moderatorDAO = moderatorDAO;
        this.modelMapperCustom = new ModeratorMapper();
    }

    @Override
    public ModeratorDTO findDTOById(long id) throws Exception {
        Moderator moderator = moderatorDAO.findById(id);

        if (moderator != null) {
            /* ! Old logic
                Archived_ModeratorDTOBuilder modDTO = new Archived_ModeratorDTOBuilder.ModeratorDTOBuilder()
                        .setId(moderator.getId())
                        .setName(moderator.getName())
                        .setUsername(moderator.getUsername())
                        .setEmail(moderator.getEmail())
                        .setPhone(moderator.getPhone())
                        .setAddress(moderator.getAddress())
                        .setEnabled(moderator.isEnabled())
                        .build();
            */

            return (ModeratorDTO) modelMapperCustom.toDTO(moderator);

        }

        return null;
    }

    @Override
    public List<ModeratorDTO> findAllDTO() {
        List<Moderator> moderators = moderatorDAO.findAll();

        /* ! Old logic
            List<ModeratorDTO> result;
            result = new ArrayList<>();
            for (Moderator moderator : moderators) {
                Archived_ModeratorDTOBuilder modDTO = new Archived_ModeratorDTOBuilder.ModeratorDTOBuilder()
                        .setId(moderator.getId())
                        .setName(moderator.getName())
                        .setUsername(moderator.getUsername())
                        .setEmail(moderator.getEmail())
                        .setPhone(moderator.getPhone())
                        .setAddress(moderator.getAddress())
                        .setEnabled(moderator.isEnabled())
                        .build();

                result.add(modDTO);
            }
        */

        return moderators.stream().map(moderator -> {
            try {
                return (ModeratorDTO)modelMapperCustom.toDTO(moderator);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
