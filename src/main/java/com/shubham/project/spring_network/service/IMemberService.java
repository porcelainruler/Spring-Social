package com.shubham.project.spring_network.service;

import com.shubham.project.spring_network.dto.request.MemberCreateDTO;
import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.exceptions.UserNotFoundException;
import com.shubham.project.spring_network.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMemberService extends IUserService{

    public List<MemberDTO> findAllDTO ();

    public MemberDTO findDTOById (long id) throws Exception;

    public MemberDTO createMember (MemberCreateDTO memberDTO) throws Exception;

    public MemberDTO updateMember (MemberCreateDTO memberDTO) throws Exception;

    public boolean deleteMember (long id) throws UserNotFoundException;
}
