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

import static com.vic.constants.OperatorInteraction.SIMULATION_EXIT_BY_OUT_OF_BOUNDARY;


public class OperatorOutOfBoundaryReturnTest {
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
            String input = "a 5\n";
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
        Assert.assertEquals(String.format(SIMULATION_EXIT_BY_OUT_OF_BOUNDARY, 0, 4), this.moveResult);
        operator.getMovePathDTOList();
    }

    @Test
    public void shouldGenerateMovePathCorrectly() {
        List<MovePathDTO> movePathDTOList = operator.getMovePathDTOList();
        Assert.assertEquals(1, movePathDTOList.size());
        Assert.assertEquals("advance 5", movePathDTOList.get(0).getReportFormatString());
    }
}
