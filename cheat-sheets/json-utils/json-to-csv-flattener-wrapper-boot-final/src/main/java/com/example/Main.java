package com.example;

import com.example.model.Person;
import com.example.util.CSVFlattener;
import com.example.util.CSVWriter;
import com.example.wrapper.PersonWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, String> argMap = parseArgs(args);
        String inputDir = argMap.getOrDefault("input.dir", "jsons");
        String outputCsv = argMap.getOrDefault("output.csv", "output.csv");
        String propsPath = argMap.getOrDefault("fields.prop", "flat-fields.properties");

        Properties props = new Properties();
        props.load(new FileInputStream(propsPath));

        ObjectMapper mapper = new ObjectMapper();
        CSVFlattener flattener = new CSVFlattener(props);
        CSVWriter writer = new CSVWriter(outputCsv);

        File folder = new File(inputDir);
        for (File file : Objects.requireNonNull(folder.listFiles((d, name) -> name.endsWith(".json")))) {
            Person person = mapper.readValue(file, Person.class);
            PersonWrapper wrapper = new PersonWrapper(person);
            Map<String, String> flatMap = flattener.flatten(wrapper);
            writer.write(flatMap);
        }

        writer.close();
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (String arg : args) {
            if (arg.startsWith("--") && arg.contains("=")) {
                String[] parts = arg.substring(2).split("=", 2);
                map.put(parts[0], parts[1]);
            }
        }
        return map;
    }
}
