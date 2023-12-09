package com.shubham.project.spring_network.constant;

public enum UNUSED_Rating {
    UNKNOWN (0), ONE_STAR (1), TWO_STAR (2), THREE_STAR (3), FOUR_STAR (4), FIVE_STAR (5);

    private final int value;

    private UNUSED_Rating(int value) {
        this.value = value;
    }

    public int getValue () {
        return this.value;
    }
}
