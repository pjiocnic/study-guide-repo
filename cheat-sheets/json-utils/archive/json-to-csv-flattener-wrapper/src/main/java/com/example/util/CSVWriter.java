package com.example.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class CSVWriter {
    public static void writeCsv(List<Map<String, String>> rows, String outFile, List<String> headers, List<String> fields) throws Exception {
        if (rows.isEmpty()) return;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(String.join(",", headers));
            writer.newLine();
            for (Map<String, String> row : rows) {
                for (int i = 0; i < fields.size(); i++) {
                    if (i > 0) writer.write(",");
                    writer.write(row.getOrDefault(fields.get(i), ""));
                }
                writer.newLine();
            }
        }
    }
}
