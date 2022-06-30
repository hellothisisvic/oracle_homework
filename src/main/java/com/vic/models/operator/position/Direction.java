package com.vic.models.operator.position;

public enum Direction {
    EAST("East"),
    SOUTH("South"),
    WEST("West"),
    NORTH("North");

    private final String description;

    Direction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public Direction nextClockwise() {
        return values()[(ordinal() + 1) % 4];
    }

    public Direction nextCounterclockwise() {
        return values()[(ordinal() + 3) % 4];
    }

    public Direction getOppositeDirection() {
        return values()[(ordinal() + 2) % 4];
    }
}
