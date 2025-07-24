package com.example.flattener;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, String> config = new HashMap<>();
        for (String arg : args) {
            if (arg.contains("=")) {
                String[] parts = arg.split("=", 2);
                config.put(parts[0].trim(), parts[1].trim());
            }
        }

        boolean includeXHeader = Boolean.parseBoolean(config.getOrDefault("csv.header.prefix.enabled", "false"));
        String inputDirPath = config.getOrDefault("input.dir", "input-jsons");
        String propsPath = config.getOrDefault("flat.fields", "flat-fields.properties");
        String outputPath = config.getOrDefault("output.csv", "target/output.csv");

        File inputDir = new File(inputDirPath);
        File[] jsonFiles = inputDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (jsonFiles == null || jsonFiles.length == 0) {
            System.err.println("No input JSON files found.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> flatRows = new ArrayList<>();
        Properties props = new Properties();
        props.load(new FileInputStream(propsPath));

        List<Object> inputObjects = new ArrayList<>();
        for (File file : jsonFiles) {
            inputObjects.addAll(Arrays.asList(mapper.readValue(file, Object[].class)));
        }

        RecursiveFlattener flattener = new RecursiveFlattener();
        flatRows = flattener.flattenObjects(inputObjects, props);

        CSVWriter.writeCSV(outputPath, flatRows, props, includeXHeader);
        System.out.println("CSV created at: " + outputPath);
    }
}
