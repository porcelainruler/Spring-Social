package com.shubham.project.spring_network.constant;

public enum apiResponseStatus {
    API_ERROR(0), API_SUCCESS (1), NOT_FOUND (2), INTERNAL_SERVER_ERROR(3);

    private final int value;

    private apiResponseStatus(int value) {
        this.value = value;
    }

    public int getValue () {
        return this.value;
    }

}
