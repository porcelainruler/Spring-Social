package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Account;
import com.shubham.project.spring_network.persistence.model.Admin;
import com.shubham.project.spring_network.persistence.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

    @Override
    void delete (Account account);
}
