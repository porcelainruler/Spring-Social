package com.shubham.project.spring_network.service;

import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import com.shubham.project.spring_network.persistence.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMemberService extends IUserService{

    public List<MemberDTO> findAllDTO ();

    public MemberDTO findDTOById (long id) throws Exception;

    public MemberDTO createModerator (MemberDTO memberDTO);

    public MemberDTO updateModerator (MemberDTO memberDTO);

    public boolean deleteModerator (long id);
}
