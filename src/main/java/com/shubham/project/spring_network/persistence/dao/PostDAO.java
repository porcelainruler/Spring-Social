package com.shubham.project.spring_network.persistence.dao;

import com.shubham.project.spring_network.persistence.model.Member;
import com.shubham.project.spring_network.persistence.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDAO extends JpaRepository<Post, Long> {
    Post findById (long id);

    @Override
    void delete(Post member);
}
