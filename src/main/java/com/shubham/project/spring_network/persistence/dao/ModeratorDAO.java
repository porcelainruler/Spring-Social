package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorDAO extends JpaRepository<Moderator, Long> {
    Moderator findByEmail(String email);

    @Override
    void delete(Moderator moderator);

}
