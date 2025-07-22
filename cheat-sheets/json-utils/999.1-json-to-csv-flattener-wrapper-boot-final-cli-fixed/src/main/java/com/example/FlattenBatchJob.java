package com.example;

import com.example.util.CSVFlattener;
import com.example.util.CSVWriter;
import com.example.wrapper.WrapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

public class FlattenBatchJob {
    public static void main(String[] args) throws Exception {
        Map<String, String> argMap = Arrays.stream(args)
            .filter(arg -> arg.contains("="))
            .map(arg -> arg.replaceFirst("--", ""))
            .map(kv -> kv.split("=", 2))
            .collect(Collectors.toMap(kv -> kv[0], kv -> kv[1]));

        String inputDir = argMap.get("input.dir");
        String outputCsv = argMap.get("output.csv");
        String propertiesFile = argMap.get("fields.prop");

        if (inputDir == null || outputCsv == null || propertiesFile == null) {
            System.err.println("Missing required arguments: input.dir, output.csv, fields.prop");
            return;
        }

        Properties props = new Properties();
        props.load(new FileInputStream(propertiesFile));

        File[] inputFiles = new File(inputDir).listFiles((dir, name) -> name.endsWith(".json"));
        if (inputFiles == null || inputFiles.length == 0) {
            System.err.println("No JSON files found in input directory.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> allRows = new ArrayList<>();
        Set<String> headerOrder = new LinkedHashSet<>();

        for (File file : inputFiles) {
            Object rawObj = mapper.readValue(file, Object.class);
            Object obj = WrapperFactory.wrap(rawObj);
            List<Map<String, String>> rows = CSVFlattener.flattenObject(obj, props);
            rows.forEach(row -> headerOrder.addAll(row.keySet()));
            allRows.addAll(rows);
        }

        CSVWriter.writeCsv(outputCsv, allRows, new ArrayList<>(headerOrder));
        System.out.println("✅ CSV written to: " + outputCsv);
    }
}