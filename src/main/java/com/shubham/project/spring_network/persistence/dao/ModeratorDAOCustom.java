package com.shubham.project.spring_network.persistence.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface ModeratorDAOCustom {

    //    @Cacheable(value = "moderator-dto")
    ModeratorDTO findDTOById (long id) throws Exception;

    List<ModeratorDTO> findAllDTO();
}
