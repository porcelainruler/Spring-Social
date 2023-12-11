package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeratorDAOCustom {

    //    @Cacheable(value = "moderator-dto")
    ModeratorDTO findDTOById (long id);

    List<ModeratorDTO> findAllDTO();
}
