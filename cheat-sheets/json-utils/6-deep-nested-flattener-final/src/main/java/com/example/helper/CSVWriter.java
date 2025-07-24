package com.example.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSVWriter {
    public static void writeToCSV(List<Map<String, String>> rows, String outputPath, Properties props) throws IOException {
        if (rows.isEmpty()) return;

        String missingValue = props.getProperty("csv.missing.value", "NaN");
        boolean includePositionalHeader = Boolean.parseBoolean(props.getProperty("csv.include.position.header", "false"));

        List<String> headers = new ArrayList<>(rows.get(0).keySet());

        try (FileWriter writer = new FileWriter(outputPath)) {

            // Optional positional header: X0, X1, X2, ...
            if (includePositionalHeader) {
                List<String> positionalHeader = new ArrayList<>();
                for (int i = 0; i < headers.size(); i++) {
                    positionalHeader.add("X" + i);
                }
                writer.write(String.join(",", positionalHeader) + "\n");
            }

            // Actual header (from keys)
            writer.write(String.join(",", headers) + "\n");

            // Write data rows
            for (Map<String, String> row : rows) {
                List<String> values = new ArrayList<>();
                for (String h : headers) {
                    values.add(Optional.ofNullable(row.get(h)).orElse(missingValue));
                }
                writer.write(String.join(",", values) + "\n");
            }
        }
    }
}