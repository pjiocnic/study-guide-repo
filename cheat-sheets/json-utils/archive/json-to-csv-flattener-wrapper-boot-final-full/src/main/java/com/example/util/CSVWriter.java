package com.example.util;

import java.io.*;
import java.util.*;

public class CSVWriter {
    private final FileWriter writer;
    private final Set<String> headerSet = new LinkedHashSet<>();
    private boolean headerWritten = false;

    public CSVWriter(String outputPath) throws IOException {
        this.writer = new FileWriter(outputPath);
    }

    public void write(Map<String, String> map) throws IOException {
        if (!headerWritten) {
            headerSet.addAll(map.keySet());
            writer.write(String.join(",", headerSet));
            writer.write("\n");
            headerWritten = true;
        }
        List<String> row = new ArrayList<>();
        for (String key : headerSet) {
            row.add(map.getOrDefault(key, ""));
        }
        writer.write(String.join(",", row));
        writer.write("\n");
    }

    public void close() throws IOException {
        writer.close();
    }
}
