
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
            processKey(flatMap, key, person);
        }
        return flatMap;
    }

    private void processKey(Map<String, String> flatMap, String key, Object root) {
        String[] tokens = key.split("\.");
        processRecursive(flatMap, tokens, 0, root, "");
    }

    private void processRecursive(Map<String, String> flatMap, String[] tokens, int index, Object current, String path) {
        if (index >= tokens.length || current == null) return;

        String token = tokens[index];

        if (token.endsWith("[*]")) {
            String fieldName = token.substring(0, token.length() - 3);
            try {
                Field f = current.getClass().getDeclaredField(fieldName);
                f.setAccessible(true);
                Object value = f.get(current);
                if (value instanceof List<?>) {
                    List<?> list = (List<?>) value;
                    for (int i = 0; i < list.size(); i++) {
                        Object item = list.get(i);
                        String newPath = path.isEmpty() ? fieldName + "[" + i + "]" : path + "." + fieldName + "[" + i + "]";
                        processRecursive(flatMap, tokens, index + 1, item, newPath);
                    }
                }
            } catch (Exception e) {
                flatMap.put(path + "." + fieldName + "[*]", "ERROR");
            }
        } else {
            try {
                Field f = current.getClass().getDeclaredField(token);
                f.setAccessible(true);
                Object value = f.get(current);
                if (index == tokens.length - 1) {
                    String finalKey = path.isEmpty() ? token : path + "." + token;
                    flatMap.put(finalKey, value != null ? value.toString() : "");
                } else {
                    String newPath = path.isEmpty() ? token : path + "." + token;
                    processRecursive(flatMap, tokens, index + 1, value, newPath);
                }
            } catch (Exception e) {
                String finalKey = path.isEmpty() ? token : path + "." + token;
                flatMap.put(finalKey, "ERROR");
            }
        }
    }
}
