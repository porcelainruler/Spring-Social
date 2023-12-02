package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import com.shubham.project.spring_network.persistence.model.Moderator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.stream.Collectors;

public class ModeratorDAOImpl implements ModeratorDAOCustom {

    private ModeratorDAO moderatorDAO;

    private ModelMapper modelMapper;

    @Autowired
    @Lazy
    public ModeratorDAOImpl(ModeratorDAO moderatorDAO, ModelMapper modelMapper) {
        this.moderatorDAO = moderatorDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public ModeratorDTO findDTOById(long id) {
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
            return modelMapper.map(moderator, ModeratorDTO.class);
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

        return moderators.stream().map(moderator -> modelMapper.map(moderator, ModeratorDTO.class)).collect(Collectors.toList());
    }
}
