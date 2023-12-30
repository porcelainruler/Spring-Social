package com.shubham.project.spring_network.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReactionDTO {

    private int id;

    private int userId;

    private int entityId;

    private String reactionType;

    private String rating;

}
