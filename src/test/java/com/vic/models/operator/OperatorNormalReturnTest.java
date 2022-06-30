package com.vic.models.operator;

import com.vic.errors.IllegalStartDirection;
import com.vic.models.SiteMap;

import com.vic.models.dto.MovePathDTO;
import com.vic.models.dto.ReportItemDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.vic.constants.OperatorInteraction.SIMULATION_EXIT_BY_QUIT;


public class OperatorNormalReturnTest {
    private SiteMap siteMap;
    private Operator operator;
    private String moveResult;

    @Before
    public void setUp() {
        try {
            String line1 = "otro";
            String line2 = "tToo";
            List<String> siteMapLineList = new ArrayList<>(2);
            siteMapLineList.add(line1);
            siteMapLineList.add(line2);
            this.siteMap = new SiteMap(siteMapLineList);
            String input = "a 3\nr\na 1\nl\na 1\nl\na 1\nl\na 3\nq\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            this.operator = new Operator(this.siteMap, 0, -1);
            this.moveResult = operator.generateMovePath();
        } catch (IllegalStartDirection e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldGenerateMoveResultStringCorrectly() {
        Assert.assertEquals(SIMULATION_EXIT_BY_QUIT, this.moveResult);
        operator.getMovePathDTOList();
    }

    @Test
    public void shouldGenerateMovePathCorrectly() {
        List<MovePathDTO> movePathDTOList = operator.getMovePathDTOList();
        Assert.assertEquals(10, movePathDTOList.size());
        Assert.assertEquals("advance 3", movePathDTOList.get(0).getReportFormatString());
        Assert.assertEquals("turn right", movePathDTOList.get(1).getReportFormatString());
        Assert.assertEquals("advance 1", movePathDTOList.get(2).getReportFormatString());
        Assert.assertEquals("turn left", movePathDTOList.get(3).getReportFormatString());
        Assert.assertEquals("advance 1", movePathDTOList.get(4).getReportFormatString());
        Assert.assertEquals("turn left", movePathDTOList.get(5).getReportFormatString());
        Assert.assertEquals("advance 1", movePathDTOList.get(6).getReportFormatString());
        Assert.assertEquals("turn left", movePathDTOList.get(7).getReportFormatString());
        Assert.assertEquals("advance 3", movePathDTOList.get(8).getReportFormatString());
        Assert.assertEquals("quit", movePathDTOList.get(9).getReportFormatString());
    }

    @Test
    public void shouldGetMoveReportItemDTOListCorrectly() {
        List<ReportItemDTO> reportItemDTOList = operator.getMoveReportItemDTOList();
        Assert.assertEquals(5, reportItemDTOList.size());

        String expectStringOne = "communication overhead                    9      9";
        Assert.assertEquals(expectStringOne, reportItemDTOList.get(0).getReportFormatString());

        String expectStringTwo = "fuel usage                               11     11";
        Assert.assertEquals(expectStringTwo, reportItemDTOList.get(1).getReportFormatString());

        String expectStringThree = "uncleared squares                         1      3";
        Assert.assertEquals(expectStringThree, reportItemDTOList.get(2).getReportFormatString());

        String expectStringFour = "destruction of protected tree             0      0";
        Assert.assertEquals(expectStringFour, reportItemDTOList.get(3).getReportFormatString());

        String expectStringFive = "paint damage to bulldozer                 1      2";
        Assert.assertEquals(expectStringFive, reportItemDTOList.get(4).getReportFormatString());
    }

    @Test
    public void shouldGetStartDirectionCorrectly() {
        Assert.assertEquals("West", operator.getStartDirection());
    }

    @Test
    public void shouldGetStartFacingDirectionCorrectly() {
        Assert.assertEquals("East", operator.getStartFacingDirection());
    }

    @Test
    public void shouldGetStartRowRelativePositionCorrectly() {
        Assert.assertEquals("Northern", operator.getStartRowRelativePosition());
    }
}

