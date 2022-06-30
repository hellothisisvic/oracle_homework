package com.vic.models.squares;

import com.vic.constants.FuelUsage;

public class PlainSquare implements Square {
    @Override
    public Integer getFirstVisitFuelUsage(){
        return FuelUsage.CLEARING_PLAIN_LAND;
    }

    @Override
    public Integer getVisitedFuelUsage(){
        return FuelUsage.CLEARED_LAND;
    }

    @Override
    public Integer getPassThroughFuelUsage(){
        return FuelUsage.CLEARED_LAND;
    }

    @Override
    public boolean willDamagePaint(boolean isPassThrough){
        return false;
    }

    @Override
    public boolean isProtectedTree(){
        return false;
    }

    @Override
    public String getSquareType() {
        return SquareType.o.name();
    }
}
