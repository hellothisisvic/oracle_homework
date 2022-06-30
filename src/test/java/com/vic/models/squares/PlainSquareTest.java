package com.vic.models.squares;

import com.vic.constants.FuelUsage;
import org.junit.Assert;
import org.junit.Test;

public class PlainSquareTest {
    @Test
    public void shouldGetSquareCostCorrectly(){
        Square square = Square.getInstance(SquareType.valueOf("o"));
        Assert.assertEquals(Integer.valueOf(FuelUsage.CLEARING_PLAIN_LAND), square.getFirstVisitFuelUsage());
        Assert.assertEquals(Integer.valueOf(FuelUsage.CLEARED_LAND), square.getVisitedFuelUsage());
        Assert.assertEquals(Integer.valueOf(FuelUsage.CLEARED_LAND), square.getPassThroughFuelUsage());
        Assert.assertEquals("o", square.getSquareType());
        Assert.assertFalse(square.willDamagePaint(false));
        Assert.assertFalse(square.isProtectedTree());
    }
}
