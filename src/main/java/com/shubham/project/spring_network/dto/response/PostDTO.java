package com.shubham.project.spring_network.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class PostDTO {

    private int id;

    private int userId;

    private String description;

    private Date dateOfCreation;

    private Date updateTime;

    private Map<String, Integer> reactionSummary;

    // Paginated values with default page 1 output in post
    private List<CommentDTO> comments;

    private int commentCount;
}
