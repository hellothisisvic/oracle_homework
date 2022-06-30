package com.vic.models.squares;

import com.vic.constants.FuelUsage;
import org.junit.Assert;
import org.junit.Test;

public class RockSquareTest {
    @Test
    public void shouldGetSquareCostCorrectly(){
        Square square = Square.getInstance(SquareType.valueOf("r"));
        Assert.assertEquals(Integer.valueOf(FuelUsage.CLEARING_ROCK_LAND), square.getFirstVisitFuelUsage());
        Assert.assertEquals(Integer.valueOf(FuelUsage.CLEARED_LAND), square.getVisitedFuelUsage());
        Assert.assertEquals(Integer.valueOf(FuelUsage.CLEARING_ROCK_LAND), square.getPassThroughFuelUsage());
        Assert.assertEquals("r", square.getSquareType());
        Assert.assertFalse(square.willDamagePaint(false));
        Assert.assertFalse(square.isProtectedTree());
    }
}
