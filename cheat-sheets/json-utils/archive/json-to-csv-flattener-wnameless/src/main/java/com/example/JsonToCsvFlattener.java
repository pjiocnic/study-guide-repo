package com.example;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JsonToCsvFlattener {

    public static void main(String[] args) throws IOException {
        Properties config = new Properties();
        config.load(new FileInputStream("config.properties"));

        String inputDir = config.getProperty("inputDir", "input-jsons");
        String outputCsv = config.getProperty("outputCsv", "output.csv");

        File[] files = new File(inputDir).listFiles((dir, name) -> name.endsWith(".json"));
        if (files == null || files.length == 0) {
            System.err.println("No JSON files found in " + inputDir);
            return;
        }

        List<Map<String, String>> rows = new ArrayList<>();
        Set<String> allKeys = new LinkedHashSet<>();

        for (File file : files) {
            String json = new String(Files.readAllBytes(file.toPath()));
            Map<String, Object> flatMap = JsonFlattener.flattenAsMap(json);

            // Convert Object values to String
            Map<String, String> stringMap = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : flatMap.entrySet()) {
                stringMap.put(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
                allKeys.add(entry.getKey());
            }
            rows.add(stringMap);
        }

        // Write to CSV
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputCsv))) {
            String[] header = allKeys.toArray(new String[0]);
            writer.writeNext(header);

            for (Map<String, String> row : rows) {
                String[] line = new String[header.length];
                for (int i = 0; i < header.length; i++) {
                    line[i] = row.getOrDefault(header[i], "");
                }
                writer.writeNext(line);
            }
        }

        System.out.println("Flattened CSV written to " + outputCsv);
    }
}
