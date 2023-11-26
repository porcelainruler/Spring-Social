package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);

    @Override
    void delete(Admin admin);

}
