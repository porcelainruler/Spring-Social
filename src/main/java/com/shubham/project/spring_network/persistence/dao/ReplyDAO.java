package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyDAO extends JpaRepository<Reply, Long> {

    Reply findById (long id);

    @Override
    void delete(Reply member);
}
