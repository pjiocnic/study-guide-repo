package com.example;

import com.example.model.Person;
import com.example.util.CSVFlattener;
import com.example.util.CSVWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        File jsonDir = new File("jsons");
        if (!jsonDir.exists() || !jsonDir.isDirectory()) {
            System.err.println("Directory 'jsons' not found.");
            return;
        }

        CSVFlattener flattener = new CSVFlattener("flat-fields.properties");
        List<Map<String, String>> allRows = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for (File file : Objects.requireNonNull(jsonDir.listFiles((d, name) -> name.endsWith(".json")))) {
            String json = new String(Files.readAllBytes(file.toPath()));
            Person person = mapper.readValue(json, Person.class);
            Map<String, String> flatMap = flattener.flatten(person);
            allRows.add(flatMap);
        }

        CSVWriter.writeCsv(allRows, "output.csv", flattener.getHeaders(), flattener.getFields());
    }
}
