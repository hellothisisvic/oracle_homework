package com.vic.models.operator.position;

import com.vic.errors.IllegalStartDirection;
import com.vic.errors.OutOfSiteBoundary;

import static com.vic.constants.ErrorMessage.ILLEGAL_START_DIRECTION;

public class Position {
    private Direction direction;
    private final Direction startDirection;
    private int x;
    private int y;
    private final int startX;
    private final int startY;
    private final int siteColumnSize;
    private final int siteRowSize;

    public Position(int startRow, int startColumn, int siteRowSize, int siteColumnSize) throws IllegalStartDirection {
        this.x = startRow;
        this.y = startColumn;
        this.direction = initialStartDirection(startRow, startColumn, siteRowSize, siteColumnSize);
        this.startDirection = this.direction;
        this.siteColumnSize = siteColumnSize;
        this.siteRowSize = siteRowSize;
        this.startX = startRow;
        this.startY = startColumn;
    }

    public int getRowPosition() {
        return this.x;
    }

    public int getColumnPosition() {
        return this.y;
    }

    public String getStartDirection() {
        return this.startDirection.getDescription();
    }

    public String getStartFacingDirection() {
        return this.startDirection.getOppositeDirection().getDescription();
    }

    public String getStartRowRelativePosition() {
        String startXRelativePosition = "";
        if (this.startX <= this.siteRowSize / 2) {
            startXRelativePosition = Direction.NORTH.getDescription() + "ern";
        } else {
            startXRelativePosition = Direction.SOUTH.getDescription() + "ern";
        }
        return startXRelativePosition;
    }

    public void turnLeft() throws OutOfSiteBoundary {
        if (this.x < 0 || this.y < 0) {
            throw new OutOfSiteBoundary("turn left at " + this.x + ":" + this.y, this.x, this.y);
        }
        this.direction = this.direction.nextCounterclockwise();
    }

    public void turnRight() throws OutOfSiteBoundary {
        if (this.x < 0 || this.y < 0) {
            throw new OutOfSiteBoundary("turn right at " + this.x + ":" + this.y, this.x, this.y);
        }
        this.direction = this.direction.nextClockwise();
    }

    public void moveForward() throws OutOfSiteBoundary {
        switch (this.direction) {
            case EAST -> decreaseColumnPosition();
            case WEST -> increaseColumnPosition();
            case NORTH -> increaseRowPosition();
            case SOUTH -> decreaseRowPosition();
        }
    }

    private void increaseRowPosition() throws OutOfSiteBoundary {
        this.x++;
        if (this.x >= this.siteRowSize) {
            throw new OutOfSiteBoundary("move advanced at " + this.x + ":" + this.y, this.x, this.y);
        }
    }

    private void decreaseRowPosition() throws OutOfSiteBoundary {
        this.x--;
        if (this.x < 0) {
            throw new OutOfSiteBoundary("move advanced at " + this.x + ":" + this.y, this.x, this.y);
        }
    }

    private void increaseColumnPosition() throws OutOfSiteBoundary {
        this.y++;
        if (this.y >= this.siteColumnSize) {
            throw new OutOfSiteBoundary("move advanced at " + this.x + ":" + this.y, this.x, this.y);
        }
    }

    private void decreaseColumnPosition() throws OutOfSiteBoundary {
        this.y--;
        if (this.y < 0) {
            throw new OutOfSiteBoundary("move advanced at " + this.x + ":" + this.y, this.x, this.y);
        }
    }

    private Direction initialStartDirection(int startX, int startY, int siteRowSize, int siteColumnSize)
            throws IllegalStartDirection {
        if (startY == -1 && startX >= 0 && startX < siteRowSize) {
            return Direction.WEST;
        } else if (startY == siteColumnSize && startX >= 0 && startX < siteRowSize) {
            return Direction.EAST;
        } else if (startX == -1 && startY >= 0 && startY < siteColumnSize) {
            return Direction.NORTH;
        } else if (startX == siteRowSize && startY >= 0 && startY < siteColumnSize) {
            return Direction.SOUTH;
        } else {
            throw new IllegalStartDirection(ILLEGAL_START_DIRECTION, startX, startY);
        }
    }
}
