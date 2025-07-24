package com.example;

import com.example.model.Person;
import com.example.helper.RecursiveFlattener;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class NestedStructureTest {
    @Test
    public void testDeepNestedFlattening() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Person> list = mapper.readValue(
            new File("data/input/nested_sample.json"),
            new TypeReference<List<Person>>() {}
        );
        Properties props = new Properties();
        props.load(new FileInputStream("flat-fields.properties"));

        List<Map<String, String>> flat = RecursiveFlattener.flattenList(list, props);
        assertFalse(flat.isEmpty());
        assertTrue(flat.get(0).containsKey("ID"));
        assertTrue(flat.get(0).containsKey("Social Platform"));
    }
}