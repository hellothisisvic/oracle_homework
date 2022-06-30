package com.vic.services;

import com.vic.errors.IllegalStartDirection;
import com.vic.models.SiteMap;
import com.vic.models.dto.MovePathDTO;
import com.vic.models.dto.ReportItemDTO;
import com.vic.models.operator.Operator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_ADVANCED_DESC;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_LEFT_DESC;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_QUIT_DESC;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_RIGHT_DESC;
import static com.vic.constants.OperatorInteraction.REPORT_COMMUNICATION_ITEM_DESCRIPTION;
import static com.vic.constants.OperatorInteraction.REPORT_FUEL_ITEM_DESCRIPTION;
import static com.vic.constants.OperatorInteraction.REPORT_PAINT_DAMAGE_ITEM_DESCRIPTION;
import static com.vic.constants.OperatorInteraction.REPORT_PROTECTED_TREE_ITEM_DESCRIPTION;
import static com.vic.constants.OperatorInteraction.REPORT_UNCLEARED_SQUARE_ITEM_DESCRIPTION;
import static com.vic.constants.OperatorInteraction.SIMULATION_EXIT_BY_QUIT;
import static org.mockito.Mockito.when;

public class SimulateRunnerTest {
    private SiteMap siteMap;
    private List<MovePathDTO> movePathDTOList;
    private List<ReportItemDTO> moveReportItemDTOList;

    Operator operator;

    @Before
    public void setUp() throws IllegalStartDirection {
        String line1 = "otro";
        String line2 = "tToo";
        List<String> siteMapLineList = new ArrayList<>(2);
        siteMapLineList.add(line1);
        siteMapLineList.add(line2);

        movePathDTOList = new ArrayList<>(10);
        movePathDTOList.add(new MovePathDTO(MOVE_INDICATE_ADVANCED_DESC, 3));
        movePathDTOList.add(new MovePathDTO(MOVE_INDICATE_RIGHT_DESC, null));
        movePathDTOList.add(new MovePathDTO(MOVE_INDICATE_ADVANCED_DESC, 1));
        movePathDTOList.add(new MovePathDTO(MOVE_INDICATE_LEFT_DESC, null));
        movePathDTOList.add(new MovePathDTO(MOVE_INDICATE_ADVANCED_DESC, 1));
        movePathDTOList.add(new MovePathDTO(MOVE_INDICATE_LEFT_DESC, null));
        movePathDTOList.add(new MovePathDTO(MOVE_INDICATE_ADVANCED_DESC, 1));
        movePathDTOList.add(new MovePathDTO(MOVE_INDICATE_LEFT_DESC, null));
        movePathDTOList.add(new MovePathDTO(MOVE_INDICATE_ADVANCED_DESC, 3));
        movePathDTOList.add(new MovePathDTO(MOVE_INDICATE_QUIT_DESC, null));

        moveReportItemDTOList = new ArrayList<>(5);
        moveReportItemDTOList.add(new ReportItemDTO(REPORT_COMMUNICATION_ITEM_DESCRIPTION, 3, 3));
        moveReportItemDTOList.add(new ReportItemDTO(REPORT_FUEL_ITEM_DESCRIPTION, 1, 1));
        moveReportItemDTOList.add(new ReportItemDTO(REPORT_UNCLEARED_SQUARE_ITEM_DESCRIPTION, 1, 3));
        moveReportItemDTOList.add(new ReportItemDTO(REPORT_PROTECTED_TREE_ITEM_DESCRIPTION, 2, 20));
        moveReportItemDTOList.add(new ReportItemDTO(REPORT_PAINT_DAMAGE_ITEM_DESCRIPTION, 2, 4));

        this.siteMap = new SiteMap(siteMapLineList);

        operator = Mockito.mock(Operator.class);
        when(operator.generateMovePath()).thenReturn(SIMULATION_EXIT_BY_QUIT);
        when(operator.getMovePathDTOList()).thenReturn(movePathDTOList);
        when(operator.getMoveReportItemDTOList()).thenReturn(moveReportItemDTOList);
        when(operator.getStartRowRelativePosition()).thenReturn("Northern");
        when(operator.getStartDirection()).thenReturn("West");
        when(operator.getStartFacingDirection()).thenReturn("East");
    }

    @Test
    public void shouldGenerateStartLocationStringCorrectly() {
        try {
            SiteMap siteMap = this.siteMap;
            SimulateRunner simulateRunner = new SimulateRunner(operator);
            simulateRunner.runSimulate(siteMap);
            String expectString = "The bulldozer is currently located at the Northern edge of the site, immediately " +
                    "to the West of the site, and facing East.";
            Assert.assertEquals(expectString, simulateRunner.generateStartLocationString());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldGenerateMoveHistoryAsStringCorrectly() {
        try {
            SiteMap siteMap = this.siteMap;
            SimulateRunner simulateRunner = new SimulateRunner(operator);
            simulateRunner.runSimulate(siteMap);
            String expectString = "advance 3, turn right, advance 1, turn left, advance 1, turn left, " +
                    "advance 1, turn left, advance 3, quit";
            Assert.assertEquals(expectString, simulateRunner.generateMoveHistoryAsString(movePathDTOList));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldGenerateReportDetailAsStringCorrectly() {
        try {
            SiteMap siteMap = this.siteMap;
            SimulateRunner simulateRunner = new SimulateRunner(operator);
            simulateRunner.runSimulate(siteMap);
            String expectString = "Item                               Quantity   Cost\n" +
                    "communication overhead                    3      3\n" +
                    "fuel usage                                1      1\n" +
                    "uncleared squares                         1      3\n" +
                    "destruction of protected tree             2     20\n" +
                    "paint damage to bulldozer                 2      4\n" +
                    "----\n" +
                    "Total                                           31";
            Assert.assertEquals(expectString, simulateRunner.generateReportDetailAsString(moveReportItemDTOList));
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
