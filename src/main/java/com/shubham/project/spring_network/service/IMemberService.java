package com.shubham.project.spring_network.service;

import com.shubham.project.spring_network.persistence.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMemberService extends IUserService{

    public List<Member> findAll ();

    public Member findByUsername ();
}
