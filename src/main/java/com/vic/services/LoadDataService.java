package com.vic.services;

import com.vic.models.SiteMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoadDataService {
    public static SiteMap loadData(String filePath) throws IOException{
        SiteMap siteMap = null;
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            siteMap = new SiteMap(stream.collect(Collectors.toList()));
        }
        return siteMap;
    }
}
