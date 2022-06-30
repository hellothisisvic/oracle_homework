package com.vic.models.squares;

import com.vic.constants.FuelUsage;
import org.junit.Assert;
import org.junit.Test;

public class TreeSquareTest {
    @Test
    public void shouldGetSquareCostCorrectly(){
        Square square = Square.getInstance(SquareType.valueOf("t"));
        Assert.assertEquals(Integer.valueOf(FuelUsage.CLEARING_TREE_LAND), square.getFirstVisitFuelUsage());
        Assert.assertEquals(Integer.valueOf(FuelUsage.CLEARED_LAND), square.getVisitedFuelUsage());
        Assert.assertEquals(Integer.valueOf(FuelUsage.CLEARING_TREE_LAND), square.getPassThroughFuelUsage());
        Assert.assertEquals("t", square.getSquareType());
        Assert.assertFalse(square.willDamagePaint(false));
        Assert.assertTrue(square.willDamagePaint(true));
        Assert.assertFalse(square.isProtectedTree());
    }
}
