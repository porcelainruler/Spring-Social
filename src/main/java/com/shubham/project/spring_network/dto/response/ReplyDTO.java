package com.shubham.project.spring_network.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ReplyDTO {

    private int id;

    private int userId;

    private int postId;

    private int commentId;

    private int replyId;

    private String message;

    private String replyType;

    private Map<String, Integer> reactionSummary;

    // Paginated values with default page 1 output in post
    private List<ReplyDTO> replies;

    private int replyCount;
}
