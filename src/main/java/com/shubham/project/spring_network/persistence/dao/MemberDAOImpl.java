package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.dto.mapper.IDTOMapper;
import com.shubham.project.spring_network.dto.mapper.MemberMapper;
import com.shubham.project.spring_network.dto.mapper.ModeratorMapper;
import com.shubham.project.spring_network.dto.response.MemberDTO;
import com.shubham.project.spring_network.dto.response.ModeratorDTO;
import com.shubham.project.spring_network.persistence.model.Member;
import com.shubham.project.spring_network.persistence.model.Moderator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.stream.Collectors;

public class MemberDAOImpl implements MemberDAOCustom {

    private final MemberDAO memberDAO;

    private final IDTOMapper<Member, MemberDTO> modelMapperCustom;

    @Autowired
    @Lazy
    public MemberDAOImpl (MemberDAO memberDAO, ModeratorMapper moderatorMapper) {
        this.memberDAO = memberDAO;
        this.modelMapperCustom = new MemberMapper();
    }

    @Override
    public MemberDTO findDTOById(long id) throws Exception {
        Member member = memberDAO.findById(id);

        if (member != null) {
            return modelMapperCustom.toDTO(member);
        }

        return null;
    }

    @Override
    public List<MemberDTO> findAllDTO() {
        List<Member> members = memberDAO.findAll();

        return members.stream().map(member -> {
            try {
                return modelMapperCustom.toDTO(member);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
