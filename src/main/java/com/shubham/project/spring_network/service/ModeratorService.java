package com.shubham.project.spring_network.service;

import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import com.shubham.project.spring_network.persistence.dao.ModeratorDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ModeratorService implements IModeratorService {
    // * Logger
    private final Logger logger = LoggerFactory.getLogger(ModeratorService.class);

    private final ModeratorDAO moderatorDAO;

    @Autowired
    public ModeratorService (ModeratorDAO moderatorDAO) {
        this.moderatorDAO = moderatorDAO;
    }

    @Override
    public List<ModeratorDTO> findAllDTO() {
        return  moderatorDAO.findAllDTO();
    }

    @Override
    public ModeratorDTO findDTOById(long id) throws Exception {
        return moderatorDAO.findDTOById(id);
    }

    @Override
    public ModeratorDTO createModerator(ModeratorDTO moderatorDTO) {
        return null;
    }

    @Override
    public ModeratorDTO updateModerator(ModeratorDTO moderatorDTO) {
        return null;
    }

    @Override
    public boolean deleteModerator(long id) {
        return false;
    }
}
