package com.shubham.project.spring_network.constant;

public enum ReactionType {
    UNKNOWN (0), POST (1), COMMENT (2), REPLY (3);

    private final int value;

    private ReactionType(int value) {
        this.value = value;
    }

    public int getValue () {
        return this.value;
    }
}
