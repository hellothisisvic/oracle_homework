package com.vic.models.operator.position;

import com.vic.errors.IllegalStartDirection;
import org.junit.Assert;
import org.junit.Test;

public class PositionGetStartRowRelativePositionTest {
    @Test
    public void shouldGetStartRowRelativePositionOnNorthCorrectly(){
        try {
            Position position = new Position(0, -1,5, 10);
            Assert.assertEquals("Northern", position.getStartRowRelativePosition());
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldGetStartRowRelativePositionOnSouthCorrectly(){
        try {
            Position position = new Position(3, -1, 5, 10);
            Assert.assertEquals("Southern", position.getStartRowRelativePosition());
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldGetStartDirectionOnNorthCorrectly(){
        try {
            Position position = new Position(-1, 0, 5, 10);
            Assert.assertEquals("North", position.getStartDirection());
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldGetStartFacingDirectionOnNorthCorrectly(){
        try {
            Position position = new Position(-1, 0, 5, 10);
            Assert.assertEquals("South", position.getStartFacingDirection());
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }
}
