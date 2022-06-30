package com.vic.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SiteMapNormalTest {
    private SiteMap siteMap;

    @Before
    public void setUp() {
        String line1 = "otro";
        String line2 = "tToo";
        List<String> siteMapLineList = new ArrayList<>(2);
        siteMapLineList.add(line1);
        siteMapLineList.add(line2);
        this.siteMap = new SiteMap(siteMapLineList);
    }

    @Test
    public void shouldGetSiteMapStringCorrectly() {
        Assert.assertEquals("otro\ntToo\n", this.siteMap.getSiteMapString());
    }

    @Test
    public void shouldGetSiteMapRowSizeCorrectly() {
        Assert.assertEquals(2, this.siteMap.getSiteMapRowSize());
    }

    @Test
    public void shouldGetSiteMapColumnSizeCorrectly() {
        Assert.assertEquals(4, this.siteMap.getSiteMapColumnSize());
    }

    @Test
    public void shouldGetUnreachableSquareCountCorrectly() {
        Assert.assertEquals(1, this.siteMap.getUnreachableSquareCount());
    }

    @Test
    public void shouldGetSquareCorrectly() {
        Assert.assertEquals("com.vic.models.squares.RockSquare",
                this.siteMap.getSquare(0, 2).getClass().getTypeName());
    }

    @Test
    public void shouldGetSquareNullWhenOutOfBoundaryOnRowsPositive() {
        Assert.assertNull(this.siteMap.getSquare(3, 1));
    }

    @Test
    public void shouldGetSquareNullWhenOutOfBoundaryOnRowsNegative() {
        Assert.assertNull(this.siteMap.getSquare(-1, 1));
    }

    @Test
    public void shouldGetSquareNullWhenOutOfBoundaryOnColumnsPositive() {
        Assert.assertNull(this.siteMap.getSquare(1, 6));
    }

    @Test
    public void shouldGetSquareNullWhenOutOfBoundaryOnColumnsNegative() {
        Assert.assertNull(this.siteMap.getSquare(1, -1));
    }
}
