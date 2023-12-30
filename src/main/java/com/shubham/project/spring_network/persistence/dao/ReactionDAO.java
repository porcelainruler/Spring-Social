package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionDAO extends JpaRepository<Reaction, Long>, ReactionDAOCustom {
}
