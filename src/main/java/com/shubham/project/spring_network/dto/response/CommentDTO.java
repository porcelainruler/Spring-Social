package com.shubham.project.spring_network.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class CommentDTO {
    private int id;

    private int userId;

    private int postId;

    private String message;

    private Map<String, Integer> reactionSummary;

    private Date dateOfCreation;

    private Date updatedAt;

    // Paginated values with default page 1 output in comment
    private List<ReplyDTO> replies;

    private int replyCount;
}
