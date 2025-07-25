package com.example;

import com.example.model.*;
import com.example.helper.RecursiveFlattener;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecursiveFlattenerTest {

    @Test
    public void testDeepNestedStructureFlattening() throws IOException {
        String json = "[{\"B\":[{\"C\":[{\"D\":[{\"g\":\"one\",\"h\":\"uno\"}]}]}]},{\"B\":[{\"C\":[{\"D\":[{\"g\":\"two\",\"h\":\"dos\"},{\"g\":\"three\",\"h\":\"tres\"}]}]}]}]";

        ObjectMapper mapper = new ObjectMapper();
        List<A> aList = Arrays.asList(mapper.readValue(json, A[].class));

        Properties props = new Properties();
        props.setProperty("B[*].C[*].D[*].g", "Y:G_Val");
        props.setProperty("B[*].C[*].D[*].h", "Y:H_Val");

        List<Map<String, String>> result = RecursiveFlattener.flattenList(aList, props);

        assertEquals(2, result.size());

        Map<String, String> row1 = result.get(0);
        assertTrue(row1.containsKey("G_Val"));
        assertEquals("one", row1.get("G_Val"));

        Map<String, String> row2 = result.get(1);
        assertTrue(row2.containsKey("G_Val"));
        assertEquals("two", row2.get("G_Val"));
    }
}