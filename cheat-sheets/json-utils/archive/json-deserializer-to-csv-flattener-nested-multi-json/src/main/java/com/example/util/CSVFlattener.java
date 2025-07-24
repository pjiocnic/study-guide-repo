
package com.example.util;

import com.example.model.Person;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

public class CSVFlattener {
    private final Properties fieldConfig;
    private final LinkedHashMap<String, String> fieldToHeaderMap = new LinkedHashMap<>();

    public CSVFlattener(String propFile) throws Exception {
        this.fieldConfig = new Properties();
        try (InputStream in = new FileInputStream(propFile)) {
            this.fieldConfig.load(in);
        }

        for (String key : fieldConfig.stringPropertyNames()) {
            String value = fieldConfig.getProperty(key);
            if (value.startsWith("Y:")) {
                fieldToHeaderMap.put(key, value.substring(2));
            } else if ("Y".equalsIgnoreCase(value)) {
                fieldToHeaderMap.put(key, key);
            }
        }
    }

    private final List<String> actualHeaders = new ArrayList<>();
    private final List<String> actualFields = new ArrayList<>();

    public List<String> getHeaders() {
        return actualHeaders;
    }

    public List<String> getFields() {
        return actualFields;
    }

    public Map<String, String> flatten(Person person) {
        Map<String, String> flatMap = new LinkedHashMap<>();
        actualHeaders.clear();
        actualFields.clear();

        for (String key : fieldToHeaderMap.keySet()) {
            String headerBase = fieldToHeaderMap.get(key);
            String[] tokens = key.split("\.");
            traverse(flatMap, tokens, 0, person, "", headerBase);
        }

        return flatMap;
    }

    private void traverse(Map<String, String> flatMap, String[] tokens, int index, Object current, String path, String headerBase) {
        if (index >= tokens.length || current == null) return;

        String token = tokens[index];
        boolean isArray = token.endsWith("[*]");
        String fieldName = isArray ? token.substring(0, token.length() - 3) : token;

        try {
            Field field = current.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(current);

            if (isArray && value instanceof List<?>) {
                List<?> list = (List<?>) value;
                for (int i = 0; i < list.size(); i++) {
                    String newPath = path.isEmpty() ? fieldName + "[" + i + "]" : path + "." + fieldName + "[" + i + "]";
                    String newHeader = headerBase + "[" + i + "]";
                    traverse(flatMap, tokens, index + 1, list.get(i), newPath, newHeader);
                }
            } else {
                if (index == tokens.length - 1) {
                    String finalKey = path.isEmpty() ? fieldName : path + "." + fieldName;
                    flatMap.put(finalKey, value != null ? value.toString() : "");
                    actualFields.add(finalKey);
                    actualHeaders.add(headerBase);
                } else {
                    String newPath = path.isEmpty() ? fieldName : path + "." + fieldName;
                    traverse(flatMap, tokens, index + 1, value, newPath, headerBase);
                }
            }
        } catch (Exception e) {
            String errorKey = path.isEmpty() ? token : path + "." + token;
            flatMap.put(errorKey, "ERROR");
        }
    }
}
