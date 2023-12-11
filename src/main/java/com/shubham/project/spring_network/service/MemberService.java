package com.shubham.project.spring_network.service;

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

    @Autowired
    public MemberService (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Member> findAll() {

        return null;
    }

    @Override
    public Member findByUsername() {

        return null;
    }
}
