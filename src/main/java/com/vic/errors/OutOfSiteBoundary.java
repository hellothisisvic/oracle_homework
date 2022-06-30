package com.vic.errors;

public class OutOfSiteBoundary extends Throwable {
    private final int x;
    private final int y;

    public OutOfSiteBoundary(String message, int x, int y) {
        super(message);
        this.x = x;
        this.y = y;
    }

    public int getRow() {
        return this.x;
    }

    public int getColumn() {
        return this.y;
    }
}
