package com.shubham.project.spring_network.constant;

public enum Rating {
    UNKNOWN (0), LIKE (1), LAUGH (2), HEART (3), ANGRY (4), SUPPORT (5), CRY (6);

    private final int value;

    private Rating(int value) {
        this.value = value;
    }

    public int getValue () {
        return this.value;
    }
}
