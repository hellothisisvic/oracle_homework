package com.vic.services;

import com.vic.models.SiteMap;
import com.vic.models.dto.MovePathDTO;
import com.vic.models.dto.ReportItemDTO;
import com.vic.models.operator.Operator;

import java.util.List;

import static com.vic.constants.OperatorInteraction.COST_LIST;
import static com.vic.constants.OperatorInteraction.EMPTY_STRING;
import static com.vic.constants.OperatorInteraction.LOCATION;
import static com.vic.constants.OperatorInteraction.MOVE_HISTORY;
import static com.vic.constants.OperatorInteraction.MOVE_PATH_SEPARATOR;
import static com.vic.constants.OperatorInteraction.NEW_LINE_STRING;
import static com.vic.constants.OperatorInteraction.REPORT_HEADLINE_COST_COLUMN_NAME;
import static com.vic.constants.OperatorInteraction.REPORT_HEADLINE_ITEM_COLUMN_NAME;
import static com.vic.constants.OperatorInteraction.REPORT_HEADLINE_QUANTITY_COLUMN_NAME;
import static com.vic.constants.OperatorInteraction.REPORT_TOTAL;
import static com.vic.constants.OperatorInteraction.REPORT_TOTAL_SEPARATE_MARK;
import static com.vic.constants.OperatorInteraction.SPACE_STRING;
import static com.vic.constants.OperatorInteraction.THANK_YOU;
import static com.vic.constants.OperatorInteraction.WELCOME;
import static com.vic.constants.SimulationParameter.INT_POSTFIX;
import static com.vic.constants.SimulationParameter.REPORT_COST_LINE_COST_SIZE;
import static com.vic.constants.SimulationParameter.REPORT_COST_LINE_DESCRIPTION_SIZE;
import static com.vic.constants.SimulationParameter.REPORT_COST_LINE_QUANTITY_SIZE;
import static com.vic.constants.SimulationParameter.REPORT_COST_LINE_TOTAL_DESCRIPTION_SIZE;
import static com.vic.constants.SimulationParameter.REPORT_COST_LINE_TOTAL_VALUE_SIZE;
import static com.vic.constants.SimulationParameter.STRING_POSTFIX;

public class SimulateRunner {
    private final Operator operator;

    public SimulateRunner(Operator operator) {
        this.operator = operator;
    }

    public void runSimulate(SiteMap siteMap) {
        printToConsole(WELCOME);
        printToConsole(siteMap.getSiteMapString());
        printToConsole(generateStartLocationString());
        printToConsole(EMPTY_STRING);
        String exitReason = this.operator.generateMovePath();
        printToConsole(EMPTY_STRING);
        printToConsole(exitReason + SPACE_STRING + MOVE_HISTORY);
        printToConsole(EMPTY_STRING);
        printToConsole(generateMoveHistoryAsString(this.operator.getMovePathDTOList()));
        printToConsole(EMPTY_STRING);
        printToConsole(COST_LIST);
        printToConsole(EMPTY_STRING);
        printToConsole(generateReportDetailAsString(this.operator.getMoveReportItemDTOList()));
        printToConsole(EMPTY_STRING);
        printToConsole(THANK_YOU);
    }

    private void printToConsole(String inputString) {
        System.out.println(inputString);
    }

    public String generateStartLocationString() {
        return String.format(
                LOCATION,
                this.operator.getStartRowRelativePosition(),
                this.operator.getStartDirection(),
                this.operator.getStartFacingDirection());
    }

    public String generateMoveHistoryAsString(List<MovePathDTO> movePathDTOList) {
        StringBuilder sb = new StringBuilder();
        String prefix = EMPTY_STRING;
        for (MovePathDTO eachDTO : movePathDTOList) {
            sb.append(prefix);
            prefix = MOVE_PATH_SEPARATOR + SPACE_STRING;
            sb.append(eachDTO.getReportFormatString());
        }
        return sb.toString();
    }

    public String generateReportDetailAsString(List<ReportItemDTO> reportItemDTOList) {
        int totalCost = reportItemDTOList.stream().mapToInt(ReportItemDTO::getCost).sum();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(REPORT_COST_LINE_DESCRIPTION_SIZE + STRING_POSTFIX, REPORT_HEADLINE_ITEM_COLUMN_NAME))
                .append(String.format(REPORT_COST_LINE_QUANTITY_SIZE + STRING_POSTFIX, REPORT_HEADLINE_QUANTITY_COLUMN_NAME))
                .append(String.format(REPORT_COST_LINE_COST_SIZE + STRING_POSTFIX, REPORT_HEADLINE_COST_COLUMN_NAME))
                .append(NEW_LINE_STRING);
        for (ReportItemDTO eachDTO : reportItemDTOList) {
            sb.append(eachDTO.getReportFormatString())
                    .append(NEW_LINE_STRING);
        }
        sb.append(REPORT_TOTAL_SEPARATE_MARK)
                .append(NEW_LINE_STRING)
                .append(String.format(REPORT_COST_LINE_TOTAL_DESCRIPTION_SIZE + STRING_POSTFIX, REPORT_TOTAL))
                .append(String.format(REPORT_COST_LINE_TOTAL_VALUE_SIZE + INT_POSTFIX, totalCost));
        return sb.toString();
    }
}
