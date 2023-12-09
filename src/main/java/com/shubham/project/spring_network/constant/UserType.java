package com.shubham.project.spring_network.constant;

public enum UserType {
    UNKNOWN (0), MEMBER (1), BUSINESS_MEMBER (2), MODERATOR (3), ADMIN (4);

    private final int value;

    private UserType(int value) {
        this.value = value;
    }

    public int getValue () {
        return this.value;
    }
}
