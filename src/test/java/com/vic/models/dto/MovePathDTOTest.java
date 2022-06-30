package com.vic.models.dto;

import org.junit.Assert;
import org.junit.Test;

import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_ADVANCED_DESC;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_LEFT_DESC;
import static com.vic.constants.OperatorInteraction.SPACE_STRING;

public class MovePathDTOTest {

    @Test
    public void shouldGetReportFormatStringWithDistanceCorrectly(){
        MovePathDTO movePathDTO = new MovePathDTO(MOVE_INDICATE_ADVANCED_DESC, 2);
        String expectString = MOVE_INDICATE_ADVANCED_DESC + SPACE_STRING + 2;
        Assert.assertEquals(expectString, movePathDTO.getReportFormatString());
    }

    @Test
    public void shouldGetReportFormatStringWithoutDistanceCorrectly(){
        MovePathDTO movePathDTO = new MovePathDTO(MOVE_INDICATE_LEFT_DESC, null);
        Assert.assertEquals(MOVE_INDICATE_LEFT_DESC, movePathDTO.getReportFormatString());
    }
}
