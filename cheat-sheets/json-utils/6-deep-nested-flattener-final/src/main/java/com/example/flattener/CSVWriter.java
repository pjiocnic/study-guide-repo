package com.example.flattener;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSVWriter {

    public static void writeCSV(String filePath, List<Map<String, String>> rows, Properties props, boolean includeXHeader) throws IOException {
        if (rows == null || rows.isEmpty()) {
            System.out.println("No data to write.");
            return;
        }

        // Get sorted header keys based on props
        Set<String> allKeys = new LinkedHashSet<>();
        for (Map<String, String> row : rows) {
            allKeys.addAll(row.keySet());
        }
        List<String> sortedKeys = new ArrayList<>(allKeys);

        try (FileWriter writer = new FileWriter(filePath)) {
            if (includeXHeader) {
                for (int i = 0; i < sortedKeys.size(); i++) {
                    writer.append("X").append(String.valueOf(i));
                    if (i < sortedKeys.size() - 1) writer.append(",");
                }
                writer.append("\n");
            }

            for (int i = 0; i < sortedKeys.size(); i++) {
                String label = props.getProperty(sortedKeys.get(i));
                if (label != null && label.startsWith("Y:")) {
                    writer.append(label.substring(2));
                } else {
                    writer.append(sortedKeys.get(i));
                }
                if (i < sortedKeys.size() - 1) writer.append(",");
            }
            writer.append("\n");

            for (Map<String, String> row : rows) {
                for (int i = 0; i < sortedKeys.size(); i++) {
                    writer.append(row.getOrDefault(sortedKeys.get(i), "NaN"));
                    if (i < sortedKeys.size() - 1) writer.append(",");
                }
                writer.append("\n");
            }
        }
    }
}
