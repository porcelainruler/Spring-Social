package com.shubham.project.spring_network.service;

import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.persistence.dao.MemberDAO;
import com.shubham.project.spring_network.persistence.model.Member;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements IMemberService {
    // * Logger
    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private ModelMapper modelMapper;
    private final MemberDAO memberDAO;

    @Autowired
    public MemberService (ModelMapper modelMapper,
                          MemberDAO memberDAO) {
        this.modelMapper = modelMapper;
        this.memberDAO = memberDAO;
    }

    @Override
    public List<MemberDTO> findAllDTO() {
        return memberDAO.findAllDTO();
    }

    @Override
    public MemberDTO findDTOById(long id) throws Exception {
        return memberDAO.findDTOById(id);
    }

    @Override
    public MemberDTO createModerator(MemberDTO memberDTO) {
        return null;
    }

    @Override
    public MemberDTO updateModerator(MemberDTO memberDTO) {
        return null;
    }

    @Override
    public boolean deleteModerator(long id) {
        return false;
    }
}
