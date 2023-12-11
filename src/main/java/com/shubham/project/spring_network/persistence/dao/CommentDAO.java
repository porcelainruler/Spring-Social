package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, Long> {

    Comment findById (long id);

    @Override
    void delete(Comment comment);
}
