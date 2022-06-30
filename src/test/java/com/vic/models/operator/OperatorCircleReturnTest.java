package com.vic.models.operator;

import com.vic.errors.IllegalStartDirection;
import com.vic.models.SiteMap;
import com.vic.models.dto.MovePathDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.vic.constants.OperatorInteraction.SIMULATION_EXIT_BY_QUIT;


public class OperatorCircleReturnTest {
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
            String input = "a 3\nr\na 1\nl\na 1\nr\nq\n";
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
        Assert.assertEquals(7, movePathDTOList.size());
        Assert.assertEquals("advance 3", movePathDTOList.get(0).getReportFormatString());
        Assert.assertEquals("turn right", movePathDTOList.get(1).getReportFormatString());
        Assert.assertEquals("advance 1", movePathDTOList.get(2).getReportFormatString());
        Assert.assertEquals("turn left", movePathDTOList.get(3).getReportFormatString());
        Assert.assertEquals("advance 1", movePathDTOList.get(4).getReportFormatString());
        Assert.assertEquals("turn right", movePathDTOList.get(5).getReportFormatString());
        Assert.assertEquals("quit", movePathDTOList.get(6).getReportFormatString());
    }
}
