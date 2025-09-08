package com.example.etl.service;

import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdsLoader {

    public List<String> loadIds(Path path) throws Exception {
        String content = Files.readString(path);
        return Arrays.stream(content.split("[,\n\r]+"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
