package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Admin;
import com.shubham.project.spring_network.persistence.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);

    Admin findByUsername(String username);

    Admin findById (long id);

    @Override
    void delete(Admin admin);

}
