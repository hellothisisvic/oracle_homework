package com.vic.errors;

public class DestructProtectedTree extends Throwable {
    private final int x;
    private final int y;

    public DestructProtectedTree(String message, int x, int y) {
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
