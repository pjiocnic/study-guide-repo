
package com.example.util;

import com.example.model.Person;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

public class CSVFlattener {
    private final Properties fieldConfig;

    public CSVFlattener(String propFile) throws Exception {
        this.fieldConfig = new Properties();
        try (InputStream in = new FileInputStream(propFile)) {
            this.fieldConfig.load(in);
        }
    }

    public Map<String, String> flatten(Person person) {
        Map<String, String> flatMap = new LinkedHashMap<>();
        for (String key : fieldConfig.stringPropertyNames()) {
            if (!"Y".equalsIgnoreCase(fieldConfig.getProperty(key))) continue;
            String[] parts = key.split("\.");
            Object current = person;

            try {
                for (String part : parts) {
                    int index = -1;
                    if (part.matches(".+\[\\d+]")) {
                        String field = part.replaceAll("\\[\\d+]", "");
                        index = Integer.parseInt(part.replaceAll(".*\\[(\\d+)]", "$1"));
                        Field f = current.getClass().getDeclaredField(field);
                        f.setAccessible(true);
                        List<?> list = (List<?>) f.get(current);
                        current = list.get(index);
                    } else {
                        Field f = current.getClass().getDeclaredField(part);
                        f.setAccessible(true);
                        current = f.get(current);
                    }
                }
                flatMap.put(key, current != null ? current.toString() : "");
            } catch (Exception e) {
                flatMap.put(key, "ERROR");
            }
        }
        return flatMap;
    }
}
