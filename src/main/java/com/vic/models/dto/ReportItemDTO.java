package com.vic.models.dto;

import static com.vic.constants.SimulationParameter.INT_POSTFIX;
import static com.vic.constants.SimulationParameter.REPORT_COST_LINE_COST_SIZE;
import static com.vic.constants.SimulationParameter.REPORT_COST_LINE_DESCRIPTION_SIZE;
import static com.vic.constants.SimulationParameter.REPORT_COST_LINE_QUANTITY_SIZE;
import static com.vic.constants.SimulationParameter.STRING_POSTFIX;

public class ReportItemDTO {
    private final String description;
    private final int quantity;
    private final int cost;

    public ReportItemDTO(String description, int quantity, int cost) {
        this.description = description;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }

    public String getReportFormatString() {
        return String.format(REPORT_COST_LINE_DESCRIPTION_SIZE + STRING_POSTFIX, this.description)
                + String.format(REPORT_COST_LINE_QUANTITY_SIZE + INT_POSTFIX, this.quantity)
                + String.format(REPORT_COST_LINE_COST_SIZE + INT_POSTFIX, this.cost);
    }
}
