package com.vic.models.operator.position;

import com.vic.errors.IllegalStartDirection;
import com.vic.errors.OutOfSiteBoundary;
import org.junit.Assert;
import org.junit.Test;

public class PositionTurnLeftTest {
    @Test
    public void shouldTurnLeftAtStartPointAtOutSideRowWithError(){
        try {
            Position position = new Position(-1, 0, 5, 10);
            position.turnLeft();
        } catch (OutOfSiteBoundary e) {
            Assert.assertEquals("turn left at -1:0", e.getMessage());
            Assert.assertEquals(-1, e.getRow());
            Assert.assertEquals(0, e.getColumn());
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldTurnLeftAtStartPointAtOutSideColumnWithError(){
        try {
            Position position = new Position(0, -1, 5, 10);
            position.turnLeft();
        } catch (OutOfSiteBoundary e) {
            Assert.assertEquals("turn left at 0:-1", e.getMessage());
            Assert.assertEquals(0, e.getRow());
            Assert.assertEquals(-1, e.getColumn());
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldTurnLeftCorrectly(){
        try {
            Position position = new Position(1, -1, 5, 10);
            position.moveForward();
            position.turnLeft();
            position.moveForward();
            Assert.assertEquals(0, position.getRowPosition());
            Assert.assertEquals(0, position.getColumnPosition());
        } catch (OutOfSiteBoundary | IllegalStartDirection e) {
            Assert.fail();
        }
    }
}
