package com.shubham.project.spring_network.constant;

public enum ApiResponseStatus {
    API_ERROR(0), API_SUCCESS (1), NOT_FOUND (2), ACCESS_DENIED (3), INTERNAL_SERVER_ERROR(4);

    private final int value;

    private ApiResponseStatus(int value) {
        this.value = value;
    }

    public int getValue () {
        return this.value;
    }

}
