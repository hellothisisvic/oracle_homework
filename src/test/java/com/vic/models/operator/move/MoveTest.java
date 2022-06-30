package com.vic.models.operator.move;

import com.vic.models.dto.MovePathDTO;
import org.junit.Assert;
import org.junit.Test;

import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_ADVANCED_DESC;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_LEFT_DESC;

public class MoveTest {
    @Test
    public void shouldConvertToMovePathDTOWithDistanceCorrectly(){
        Move move = new Move(MoveType.ADVANCE, 2);
        MovePathDTO movePathDTO = new MovePathDTO(MOVE_INDICATE_ADVANCED_DESC, 2);
        Assert.assertEquals(movePathDTO.getReportFormatString(), move.convertToMovePathDTO().getReportFormatString());
        Assert.assertEquals(Integer.valueOf(2), move.getDistance());
        Assert.assertEquals(MoveType.ADVANCE, move.getType());
        Assert.assertEquals("a", move.getType().getShortName());
    }

    @Test
    public void shouldConvertToMovePathDTOWithoutDistanceCorrectly(){
        Move move = new Move(MoveType.LEFT);
        MovePathDTO movePathDTO = new MovePathDTO(MOVE_INDICATE_LEFT_DESC, null);
        Assert.assertEquals(movePathDTO.getReportFormatString(), move.convertToMovePathDTO().getReportFormatString());
        Assert.assertNull(move.getDistance());
        Assert.assertEquals(MoveType.LEFT, move.getType());
        Assert.assertEquals("l", move.getType().getShortName());
    }
}
