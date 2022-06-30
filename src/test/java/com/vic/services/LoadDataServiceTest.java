package com.vic.services;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.nio.file.Files;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

public class LoadDataServiceTest {
    @Test
    public void shouldGenerateStartLocationStringCorrectly() {
        Stream<String> stringStream = Stream.of("otro");
        try (MockedStatic<Files> files = Mockito.mockStatic(Files.class)) {
            files.when(() -> Files.lines(any()))
                    .thenReturn(stringStream);
            String expectString = "otro\n";
            Assert.assertEquals(expectString, LoadDataService.loadData("empty").getSiteMapString());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
