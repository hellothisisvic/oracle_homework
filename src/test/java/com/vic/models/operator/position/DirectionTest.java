package com.vic.models.operator.position;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DirectionTest {
    private Direction direction;

    @Before
    public void setUp(){
        this.direction = Direction.EAST;
    }

    @Test
    public void shouldGetNextClockwiseCorrectly(){
        Assert.assertEquals(direction.nextClockwise(), Direction.SOUTH);
    }

    @Test
    public void shouldGetNextCounterclockwiseCorrectly(){
        Assert.assertEquals(direction.nextCounterclockwise(), Direction.NORTH);
    }

    @Test
    public void shouldGetOppositeDirectionCorrectly(){
        Assert.assertEquals(direction.getOppositeDirection(), Direction.WEST);
    }
}
