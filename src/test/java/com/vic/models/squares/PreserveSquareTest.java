package com.vic.models.squares;

import org.junit.Assert;
import org.junit.Test;

public class PreserveSquareTest {
    @Test
    public void shouldGetSquareCostCorrectly(){
        Square square = Square.getInstance(SquareType.valueOf("T"));
        Assert.assertNull(square.getFirstVisitFuelUsage());
        Assert.assertNull(square.getVisitedFuelUsage());
        Assert.assertNull(square.getPassThroughFuelUsage());
        Assert.assertEquals("T", square.getSquareType());
        Assert.assertFalse(square.willDamagePaint(false));
        Assert.assertTrue(square.isProtectedTree());
    }
}
