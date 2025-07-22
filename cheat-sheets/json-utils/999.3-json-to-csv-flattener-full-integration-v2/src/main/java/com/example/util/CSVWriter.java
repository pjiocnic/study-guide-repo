package com.example.util;

import java.io.FileWriter;
import java.util.*;

public class CSVWriter {
    public static void writeCsv(String fileName, List<Map<String, String>> rows, List<String> headers) throws Exception {
        FileWriter writer = new FileWriter(fileName);
        writer.write(String.join(",", headers) + "\n");
        for (Map<String, String> row : rows) {
            List<String> vals = new ArrayList<>();
            for (String h : headers) {
                vals.add(row.getOrDefault(h, ""));
            }
            writer.write(String.join(",", vals) + "\n");
        }
        writer.close();
    }
}