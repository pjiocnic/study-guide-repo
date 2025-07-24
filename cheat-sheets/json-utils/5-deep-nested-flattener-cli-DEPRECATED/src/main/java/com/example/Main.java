package com.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.example.helper.RecursiveFlattener;
import com.example.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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