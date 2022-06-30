package com.vic.models.operator.position;

import com.vic.errors.IllegalStartDirection;
import com.vic.errors.OutOfSiteBoundary;
import org.junit.Assert;
import org.junit.Test;

public class PositionMoveForwardTest {
    @Test
    public void shouldMoveForwardOutOfRowBoundaryOnSouth(){
        try {
            Position position = new Position(-1, 0, 2, 2);
            position.moveForward();
            position.moveForward();
            position.moveForward();
        } catch (OutOfSiteBoundary e) {
            Assert.assertEquals("move advanced at 2:0", e.getMessage());
            Assert.assertEquals(2, e.getRow());
            Assert.assertEquals(0, e.getColumn());
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldMoveForwardOutOfRowBoundaryOnNorth(){
        try {
            Position position = new Position(2, 0, 2, 2);
            position.moveForward();
            position.moveForward();
            position.moveForward();
        } catch (OutOfSiteBoundary e) {
            Assert.assertEquals("move advanced at -1:0", e.getMessage());
            Assert.assertEquals(-1, e.getRow());
            Assert.assertEquals(0, e.getColumn());
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldMoveForwardOutOfColumnBoundaryOnWest(){
        try {
            Position position = new Position(0, -1, 2, 2);
            position.moveForward();
            position.moveForward();
            position.moveForward();
        } catch (OutOfSiteBoundary e) {
            Assert.assertEquals("move advanced at 0:2", e.getMessage());
            Assert.assertEquals(0, e.getRow());
            Assert.assertEquals(2, e.getColumn());
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldMoveForwardOutOfColumnBoundaryOnEast(){
        try {
            Position position = new Position(0, 2, 2, 2);
            position.moveForward();
            position.moveForward();
            position.moveForward();
        } catch (OutOfSiteBoundary e) {
            Assert.assertEquals("move advanced at 0:-1", e.getMessage());
            Assert.assertEquals(0, e.getRow());
            Assert.assertEquals(-1, e.getColumn());
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldMoveEastCorrectly(){
        try {
            Position position = new Position(0, -1, 5, 10);
            position.moveForward();
            position.moveForward();
            Assert.assertEquals(0, position.getRowPosition());
            Assert.assertEquals(1, position.getColumnPosition());
        } catch (OutOfSiteBoundary | IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldMoveWestCorrectly(){
        try {
            Position position = new Position(0, 10, 5, 10);
            position.moveForward();
            position.moveForward();
            Assert.assertEquals(0, position.getRowPosition());
            Assert.assertEquals(8, position.getColumnPosition());
        } catch (OutOfSiteBoundary | IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldMoveNorthCorrectly(){
        try {
            Position position = new Position(5, 0, 5, 10);
            position.moveForward();
            position.moveForward();
            Assert.assertEquals(3, position.getRowPosition());
            Assert.assertEquals(0, position.getColumnPosition());
        } catch (OutOfSiteBoundary | IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldMoveSouthCorrectly(){
        try {
            Position position = new Position(-1, 0, 5, 10);
            position.moveForward();
            position.moveForward();
            Assert.assertEquals(1, position.getRowPosition());
            Assert.assertEquals(0, position.getColumnPosition());
        } catch (OutOfSiteBoundary | IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldGetIllegalStartDirectionWithColumnErrorWhenOutOfBoundaryOnSouth(){
        try {
            Position position = new Position(-1, 3, 2, 2);
        } catch (IllegalStartDirection e) {
            Assert.assertEquals("Start direction is unacceptable", e.getMessage());
            Assert.assertEquals(-1, e.getRow());
            Assert.assertEquals(3, e.getColumn());
        }
    }

    @Test
    public void shouldGetIllegalStartDirectionWithColumnErrorWhenOutOfBoundaryOnNorth(){
        try {
            Position position = new Position(2, 3, 2, 2);
        } catch (IllegalStartDirection e) {
            Assert.assertEquals("Start direction is unacceptable", e.getMessage());
            Assert.assertEquals(2, e.getRow());
            Assert.assertEquals(3, e.getColumn());
        }
    }

    @Test
    public void shouldGetIllegalStartDirectionWithColumnErrorWhenOutOfBoundaryOnWest(){
        try {
            Position position = new Position(3, 2, 2, 2);
        } catch (IllegalStartDirection e) {
            Assert.assertEquals("Start direction is unacceptable", e.getMessage());
            Assert.assertEquals(3, e.getRow());
            Assert.assertEquals(2, e.getColumn());
        }
    }

    @Test
    public void shouldGetIllegalStartDirectionWithColumnErrorWhenOutOfBoundaryOnEast(){
        try {
            Position position = new Position(3, -1, 2, 2);
        } catch (IllegalStartDirection e) {
            Assert.assertEquals("Start direction is unacceptable", e.getMessage());
            Assert.assertEquals(3, e.getRow());
            Assert.assertEquals(-1, e.getColumn());
        }
    }

    @Test
    public void shouldGetIllegalStartDirectionWithRowErrorWhenOutOfBoundaryOnWest(){
        try {
            Position position = new Position(-1, 2, 2, 2);
        } catch (IllegalStartDirection e) {
            Assert.assertEquals("Start direction is unacceptable", e.getMessage());
            Assert.assertEquals(-1, e.getRow());
            Assert.assertEquals(2, e.getColumn());
        }
    }

    @Test
    public void shouldGetIllegalStartDirectionWithRowErrorWhenOutOfBoundaryOnSouth(){
        try {
            Position position = new Position(2, -1, 2, 2);
        } catch (IllegalStartDirection e) {
            Assert.assertEquals("Start direction is unacceptable", e.getMessage());
            Assert.assertEquals(2, e.getRow());
            Assert.assertEquals(-1, e.getColumn());
        }
    }
}
