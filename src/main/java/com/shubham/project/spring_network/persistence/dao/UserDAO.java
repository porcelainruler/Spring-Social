package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Member;
import com.shubham.project.spring_network.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findById (long id);

    @Override
    void delete(User user);
}
