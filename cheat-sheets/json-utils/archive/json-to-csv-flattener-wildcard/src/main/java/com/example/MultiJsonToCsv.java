
package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MultiJsonToCsv {

    public static void main(String[] args) throws IOException {
        String inputDir = "input_json";
        String outputCsv = "flattened_output.csv";

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> allRows = new ArrayList<>();
        Set<String> headers = new TreeSet<>();

        Properties props = new Properties();
        try (InputStream in = new FileInputStream("src/main/resources/flatten-config.properties")) {
            props.load(in);
        }

        Set<String> skipFields = new HashSet<>();
        String skipConfig = props.getProperty("skip.fields", "");
        for (String field : skipConfig.split(",")) {
            skipFields.add(field.trim());
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(inputDir), "*.json")) {
            for (Path filePath : stream) {
                JsonNode root = mapper.readTree(filePath.toFile());

                if (root.isArray()) {
                    for (JsonNode node : root) {
                        Map<String, String> flat = new HashMap<>();
                        flattenJson("", node, flat, skipFields);
                        headers.addAll(flat.keySet());
                        allRows.add(flat);
                    }
                } else {
                    Map<String, String> flat = new HashMap<>();
                    flattenJson("", root, flat, skipFields);
                    headers.addAll(flat.keySet());
                    allRows.add(flat);
                }
            }
        }

        try (FileWriter writer = new FileWriter(outputCsv)) {
            List<String> headerList = new ArrayList<>(headers);
            writer.write(String.join(",", headerList) + "\n");

            for (Map<String, String> row : allRows) {
                List<String> values = new ArrayList<>();
                for (String h : headerList) {
                    values.add(escapeCsv(row.getOrDefault(h, "")));
                }
                writer.write(String.join(",", values) + "\n");
            }

            System.out.println("✅ CSV written to " + outputCsv);
        }
    }

    private static void flattenJson(String prefix, JsonNode node, Map<String, String> flatMap, Set<String> skipFields) {
        if (shouldSkip(prefix, skipFields)) return;

        if (node.isObject()) {
            node.fields().forEachRemaining(entry -> {
                String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
                flattenJson(key, entry.getValue(), flatMap, skipFields);
            });
        } else if (node.isArray()) {
            int index = 0;
            for (JsonNode element : node) {
                flattenJson(prefix + "[" + index + "]", element, flatMap, skipFields);
                index++;
            }
        } else {
            flatMap.put(prefix, node.asText());
        }
    }

    private static boolean shouldSkip(String key, Set<String> skipFields) {
        for (String pattern : skipFields) {
            if (pattern.contains("*")) {
                String regex = pattern.replace(".", "\\.")
                                      .replace("[", "\\[")
                                      .replace("]", "\\]")
                                      .replace("*", ".*");
                if (key.matches(regex)) {
                    return true;
                }
            } else if (key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    private static String escapeCsv(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"")) {
            return "\"" + value.replace("\"", "\"\") + "\"";
        }
        return value;
    }
}
