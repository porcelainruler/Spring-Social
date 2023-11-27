package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Member;
import com.shubham.project.spring_network.persistence.model.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorDAO extends JpaRepository<Moderator, Long> {
    Moderator findByEmail(String email);

    Moderator findByUsername(String username);

    @Override
    void delete(Moderator moderator);

}
