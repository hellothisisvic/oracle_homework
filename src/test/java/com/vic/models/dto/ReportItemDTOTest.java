package com.vic.models.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.vic.constants.OperatorInteraction.REPORT_COMMUNICATION_ITEM_DESCRIPTION;

public class ReportItemDTOTest {
    private ReportItemDTO reportItemDTO;

    @Before
    public void setUp(){
        this.reportItemDTO = new ReportItemDTO(REPORT_COMMUNICATION_ITEM_DESCRIPTION, 1, 1);
    }

    @Test
    public void shouldGetReportFormatStringCorrectly(){
        String expectString = "communication overhead                    1      1";
        Assert.assertEquals(expectString, reportItemDTO.getReportFormatString());
    }
}
