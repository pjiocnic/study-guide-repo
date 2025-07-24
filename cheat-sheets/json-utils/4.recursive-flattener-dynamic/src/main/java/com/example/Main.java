package com.example;

import com.example.helper.RecursiveFlattener;
import com.example.model.A;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        String inputDirPath = "data/input";
        String propFilePath = "flat-fields.properties";
        String outputPath = "data/output.csv";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--input.dir") && i + 1 < args.length) inputDirPath = args[++i];
            if (args[i].equals("--fields.prop") && i + 1 < args.length) propFilePath = args[++i];
            if (args[i].equals("--output.csv") && i + 1 < args.length) outputPath = args[++i];
        }

        List<Path> inputFiles = Files.walk(Paths.get(inputDirPath))
                                     .filter(p -> p.toString().endsWith(".json"))
                                     .collect(Collectors.toList());

        List<A> fullList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (Path p : inputFiles) {
            A[] arr = mapper.readValue(p.toFile(), A[].class);
            fullList.addAll(Arrays.asList(arr));
        }

        Properties props = new Properties();
        try (InputStream in = new FileInputStream(propFilePath)) {
            props.load(in);
        }

        List<Map<String, String>> rows = RecursiveFlattener.flattenList(fullList, props);

        if (!rows.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                List<String> headers = new ArrayList<>(rows.get(0).keySet());
                writer.write(String.join(",", headers));
                writer.newLine();

                for (Map<String, String> row : rows) {
                    List<String> vals = headers.stream()
                        .map(h -> row.getOrDefault(h, "NaN"))
                        .collect(Collectors.toList());
                    writer.write(String.join(",", vals));
                    writer.newLine();
                }
            }
            System.out.println("CSV generated: " + outputPath);
        } else {
            System.out.println("No rows flattened.");
        }
    }
}