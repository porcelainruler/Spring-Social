package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO extends JpaRepository<Member, Long>, MemberDAOCustom {
    Member findByEmail(String email);

    Member findByUsername(String username);

    Member findById (long id);

    @Override
    void delete(Member member);

}
