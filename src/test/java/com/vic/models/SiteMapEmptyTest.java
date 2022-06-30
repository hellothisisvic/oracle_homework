package com.vic.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.vic.constants.ErrorMessage.ILLEGAL_SITEMAP;

public class SiteMapEmptyTest {
    @Test
    public void shouldGetSizeZeroExceptionWhenCreating() {
        List<String> siteMapLineList = new ArrayList<>();
        try {
            new SiteMap(siteMapLineList);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(ILLEGAL_SITEMAP, e.getMessage());
        }
    }

    @Test
    public void shouldGetNullExceptionWhenCreating() {
        try {
            new SiteMap(null);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(ILLEGAL_SITEMAP, e.getMessage());
        }
    }
}
