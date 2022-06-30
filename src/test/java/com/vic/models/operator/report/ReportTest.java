package com.vic.models.operator.report;

import com.vic.models.dto.ReportItemDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.vic.constants.ErrorMessage.ILLEGAL_FUEL_CONSUME_METRIC;

public class ReportTest {
    @Test
    public void shouldCalculateConsumeFuelCorrectly() {
        Report report = new Report(2);
        report.consumeFuel(2);
        report.damagePaint();
        report.removeUnclear();
        report.damageProtectedTree();
        report.doCommunicate();

        List<ReportItemDTO> reportItemDTOList = report.getReportItemDTO();
        Assert.assertEquals(5, reportItemDTOList.size());

        String expectStringOne = "communication overhead                    1      1";
        Assert.assertEquals(expectStringOne, reportItemDTOList.get(0).getReportFormatString());

        String expectStringTwo = "fuel usage                                2      2";
        Assert.assertEquals(expectStringTwo, reportItemDTOList.get(1).getReportFormatString());

        String expectStringThree = "uncleared squares                         1      3";
        Assert.assertEquals(expectStringThree, reportItemDTOList.get(2).getReportFormatString());

        String expectStringFour = "destruction of protected tree             1     10";
        Assert.assertEquals(expectStringFour, reportItemDTOList.get(3).getReportFormatString());

        String expectStringFive = "paint damage to bulldozer                 1      2";
        Assert.assertEquals(expectStringFive, reportItemDTOList.get(4).getReportFormatString());
    }

    @Test
    public void shouldCalculateConsumeFuelErrorWhenInputNull() {
        Report report = new Report(2);
        try {
            report.consumeFuel(null);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(ILLEGAL_FUEL_CONSUME_METRIC, e.getMessage());
        }
    }
}
