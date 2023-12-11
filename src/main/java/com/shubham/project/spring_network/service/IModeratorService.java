package com.shubham.project.spring_network.service;

import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IModeratorService extends IUserService{

    public List<ModeratorDTO> findAllDTO ();

    public ModeratorDTO findDTOById (long id);
}
