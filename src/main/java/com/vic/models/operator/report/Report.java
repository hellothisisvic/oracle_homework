package com.vic.models.operator.report;

import com.vic.models.dto.ReportItemDTO;

import java.util.ArrayList;
import java.util.List;

import static com.vic.constants.Cost.PER_COMMUNICATION;
import static com.vic.constants.Cost.PER_FUEL_UNIT;
import static com.vic.constants.Cost.PER_PAINT_REPAIR;
import static com.vic.constants.Cost.PER_PROTECTED_TREE;
import static com.vic.constants.Cost.PER_UNCLEARED_SQUARE;
import static com.vic.constants.ErrorMessage.ILLEGAL_FUEL_CONSUME_METRIC;
import static com.vic.constants.OperatorInteraction.REPORT_COMMUNICATION_ITEM_DESCRIPTION;
import static com.vic.constants.OperatorInteraction.REPORT_FUEL_ITEM_DESCRIPTION;
import static com.vic.constants.OperatorInteraction.REPORT_PAINT_DAMAGE_ITEM_DESCRIPTION;
import static com.vic.constants.OperatorInteraction.REPORT_PROTECTED_TREE_ITEM_DESCRIPTION;
import static com.vic.constants.OperatorInteraction.REPORT_UNCLEARED_SQUARE_ITEM_DESCRIPTION;

public class Report {
    private final ReportItem fuel;
    private final ReportItem communicate;
    private final ReportItem unclear;
    private final ReportItem protectedTree;
    private final ReportItem paintDamage;

    public Report(int unclear) {
        this.unclear = new ReportItem(unclear, REPORT_UNCLEARED_SQUARE_ITEM_DESCRIPTION, PER_UNCLEARED_SQUARE);
        this.fuel = new ReportItem(REPORT_FUEL_ITEM_DESCRIPTION, PER_FUEL_UNIT);
        this.communicate = new ReportItem(REPORT_COMMUNICATION_ITEM_DESCRIPTION, PER_COMMUNICATION);
        this.protectedTree = new ReportItem(REPORT_PROTECTED_TREE_ITEM_DESCRIPTION, PER_PROTECTED_TREE);
        this.paintDamage = new ReportItem(REPORT_PAINT_DAMAGE_ITEM_DESCRIPTION, PER_PAINT_REPAIR);
    }

    public void consumeFuel(Integer unit) throws IllegalArgumentException {
        if (unit == null) {
            throw new IllegalArgumentException(ILLEGAL_FUEL_CONSUME_METRIC);
        }
        this.fuel.increaseQuantity(unit);
    }

    public void doCommunicate() {
        this.communicate.increaseQuantity();
    }

    public void removeUnclear() {
        this.unclear.decreaseQuantity();
    }

    public void damageProtectedTree() {
        this.protectedTree.increaseQuantity();
    }

    public void damagePaint() {
        this.paintDamage.increaseQuantity();
    }

    public List<ReportItemDTO> getReportItemDTO() {
        List<ReportItemDTO> dtoList = new ArrayList<>(5);
        dtoList.add(this.communicate.convertToDTO());
        dtoList.add(this.fuel.convertToDTO());
        dtoList.add(this.unclear.convertToDTO());
        dtoList.add(this.protectedTree.convertToDTO());
        dtoList.add(this.paintDamage.convertToDTO());
        return dtoList;
    }
}
