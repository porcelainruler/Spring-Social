package com.shubham.project.spring_network.constant;

public enum Platform {
    UNKNOWN (0), SPRING_SOCIAL (1);

    private final int value;

    private Platform(int value) {
        this.value = value;
    }

    public int getValue () {
        return this.value;
    }
}
