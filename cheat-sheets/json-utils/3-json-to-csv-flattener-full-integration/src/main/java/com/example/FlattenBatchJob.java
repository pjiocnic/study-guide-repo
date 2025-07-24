package com.example;

import com.example.helper.LocationHelper;
import com.example.model.*;
import com.example.util.CSVWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
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

        // Phase 1: Determine max array size
        int maxLocations = 0;
        for (File file : inputFiles) {
            Person p = mapper.readValue(file, Person.class);
            if (p.getAddress() != null && p.getAddress().getLocations() != null)
                maxLocations = Math.max(maxLocations, p.getAddress().getLocations().size());
        }

        // Phase 2: Flatten each
        for (File file : inputFiles) {
            Person p = mapper.readValue(file, Person.class);
            Map<String, String> row = new LinkedHashMap<>();

            if (props.containsKey("id") && props.getProperty("id").startsWith("Y"))
                row.put(props.getProperty("id").split(":")[1], String.valueOf(p.getId()));
            if (props.containsKey("name") && props.getProperty("name").startsWith("Y"))
                row.put(props.getProperty("name").split(":")[1], p.getName());
            if (p.getAddress() != null && props.containsKey("address.city") && props.getProperty("address.city").startsWith("Y"))
                row.put(props.getProperty("address.city").split(":")[1], p.getAddress().getCity());

            if (p.getAddress() != null && p.getAddress().getLocations() != null) {
                Map<String, String> locs = LocationHelper.flatten(p.getAddress().getLocations(), maxLocations, props);
                row.putAll(locs);
            }

            headerOrder.addAll(row.keySet());
            allRows.add(row);
        }

        CSVWriter.writeCsv(outputCsv, allRows, new ArrayList<>(headerOrder));
        System.out.println("✅ Wrote: " + outputCsv);
    }
}