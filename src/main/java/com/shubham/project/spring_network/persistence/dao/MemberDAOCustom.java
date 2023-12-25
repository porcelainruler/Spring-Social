package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDAOCustom {

    MemberDTO findDTOById (long id) throws Exception;

    List<MemberDTO> findAllDTO();
}
