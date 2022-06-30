package com.vic.models.squares;

public class PreserveSquare implements Square {
    @Override
    public Integer getFirstVisitFuelUsage() {
        return null;
    }

    @Override
    public Integer getVisitedFuelUsage() {
        return null;
    }

    @Override
    public Integer getPassThroughFuelUsage() {
        return null;
    }

    @Override
    public boolean willDamagePaint(boolean isPassThrough) {
        return false;
    }

    @Override
    public boolean isProtectedTree() {
        return true;
    }

    @Override
    public String getSquareType() {
        return SquareType.T.name();
    }
}
