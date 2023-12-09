package com.shubham.project.spring_network.constant;

public enum AccountStatus {
    UNKNOWN (0), ENABLED (1), SUSPENDED (2), BLOCKED (3), DISABLED (4), DELETED(5);

    private final int value;

    private AccountStatus(int value) {
        this.value = value;
    }

    public int getValue () {
        return this.value;
    }
}
