package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSVWriter {
    public static void writeToCSV(List<Map<String, String>> rows, String outputPath) throws IOException {
        if (rows.isEmpty()) return;

        List<String> headers = new ArrayList<>(rows.get(0).keySet());
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write(String.join(",", headers) + "\n");
            for (Map<String, String> row : rows) {
                List<String> values = new ArrayList<>();
                for (String h : headers) {
                    values.add(Optional.ofNullable(row.get(h)).orElse("NaN"));
                }
                writer.write(String.join(",", values) + "\n");
            }
        }
    }
}