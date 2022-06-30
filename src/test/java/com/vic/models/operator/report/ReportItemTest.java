package com.vic.models.operator.report;

import com.vic.models.dto.ReportItemDTO;
import org.junit.Assert;
import org.junit.Test;

import static com.vic.constants.OperatorInteraction.REPORT_COMMUNICATION_ITEM_DESCRIPTION;

public class ReportItemTest {
    @Test
    public void shouldIncreaseQuantityCorrectly(){
        ReportItem reportItem = new ReportItem(REPORT_COMMUNICATION_ITEM_DESCRIPTION, 1);
        reportItem.increaseQuantity();
        ReportItemDTO reportItemDTO = reportItem.convertToDTO();
        String expectString = "communication overhead                    1      1";
        Assert.assertEquals(expectString, reportItemDTO.getReportFormatString());
    }

    @Test
    public void shouldDecreaseQuantityCorrectly(){
        ReportItem reportItem = new ReportItem(2, REPORT_COMMUNICATION_ITEM_DESCRIPTION, 1);
        reportItem.decreaseQuantity();
        ReportItemDTO reportItemDTO = reportItem.convertToDTO();
        String expectString = "communication overhead                    1      1";
        Assert.assertEquals(expectString, reportItemDTO.getReportFormatString());
    }

    @Test
    public void shouldIncreaseQuantityWithAmountCorrectly(){
        ReportItem reportItem = new ReportItem(REPORT_COMMUNICATION_ITEM_DESCRIPTION, 1);
        reportItem.increaseQuantity(2);
        ReportItemDTO reportItemDTO = reportItem.convertToDTO();
        String expectString = "communication overhead                    2      2";
        Assert.assertEquals(expectString, reportItemDTO.getReportFormatString());
    }
}
