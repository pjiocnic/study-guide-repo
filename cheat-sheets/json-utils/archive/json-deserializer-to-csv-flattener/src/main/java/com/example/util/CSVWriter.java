
package com.example.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CSVWriter {
    public static void writeCsv(List<Map<String, String>> flatData, String outputPath) throws Exception {
        if (flatData.isEmpty()) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            Set<String> headers = flatData.get(0).keySet();
            writer.write(String.join(",", headers));
            writer.newLine();

            for (Map<String, String> row : flatData) {
                writer.write(headers.stream().map(h -> row.getOrDefault(h, "")).collect(Collectors.joining(",")));
                writer.newLine();
            }
        }
    }
}
