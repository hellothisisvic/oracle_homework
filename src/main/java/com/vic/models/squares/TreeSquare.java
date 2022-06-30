package com.vic.models.squares;

import com.vic.constants.FuelUsage;

public class TreeSquare implements Square {
    @Override
    public Integer getFirstVisitFuelUsage(){
        return FuelUsage.CLEARING_TREE_LAND;
    }

    @Override
    public Integer getVisitedFuelUsage(){
        return FuelUsage.CLEARED_LAND;
    }

    @Override
    public Integer getPassThroughFuelUsage(){
        return FuelUsage.CLEARING_TREE_LAND;
    }

    @Override
    public boolean willDamagePaint(boolean isPassThrough){
        return isPassThrough;
    }

    @Override
    public boolean isProtectedTree(){
        return false;
    }

    @Override
    public String getSquareType() {
        return SquareType.t.name();
    }
}
