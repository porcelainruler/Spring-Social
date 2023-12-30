package com.shubham.project.spring_network.constant;

public enum ReplyType {
    UNKNOWN (0), COMMENT (1), REPLY (2);

    private final int value;

    private ReplyType (int value) {
        this.value = value;
    }

    public int getValue () {
        return this.value;
    }
}
