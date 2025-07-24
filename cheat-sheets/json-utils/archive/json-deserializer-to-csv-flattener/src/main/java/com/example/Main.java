
package com.example;

import com.example.model.Person;
import com.example.util.CSVFlattener;
import com.example.util.CSVWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(new File("input.json"), Person.class);

        CSVFlattener flattener = new CSVFlattener("flat-fields.properties");
        Map<String, String> flatMap = flattener.flatten(person);

        List<Map<String, String>> csvData = new ArrayList<>();
        csvData.add(flatMap);

        CSVWriter.writeCsv(csvData, "output.csv");
    }
}
