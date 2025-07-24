package com.example;

import com.example.model.Person;
import com.example.helper.RecursiveFlattener;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputDir = "data/input";
        String outputCsv = "data/output/output.csv";
        String flatFieldsPath = "flat-fields.properties";

        Properties props = new Properties();
        try (InputStream in = new FileInputStream(flatFieldsPath)) {
            props.load(in);
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> allRows = new ArrayList<>();

        Files.createDirectories(Paths.get("data/output"));

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(inputDir), "*.json")) {
            for (Path entry : stream) {
                List<Person> people = mapper.readValue(entry.toFile(), new TypeReference<List<Person>>() {});
                List<Map<String, String>> flattened = RecursiveFlattener.flattenList(people, props);
                allRows.addAll(flattened);
            }
        }

        if (!allRows.isEmpty()) {
            CSVWriter.writeToCSV(allRows, outputCsv);
            System.out.println("CSV written to: " + outputCsv);
        } else {
            System.out.println("No rows found to write.");
        }
    }
}