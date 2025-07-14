package com.example.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.example.model.Person;

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
            String[] tokens = key.split("\\.");
            resolvePath(flatMap, tokens, 0, person, "");
        }
        return flatMap;
    }

    private void resolvePath(Map<String, String> flatMap, String[] tokens, int index, Object current, String path) {
        if (index >= tokens.length || current == null) return;

        String token = tokens[index];
        boolean isArrayWildcard = token.endsWith("[*]");
        String fieldName = isArrayWildcard ? token.substring(0, token.length() - 3) : token;

        try {
            Field field = current.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(current);

            if (isArrayWildcard && value instanceof List<?>) {
                List<?> list = (List<?>) value;
                for (int i = 0; i < list.size(); i++) {
                    Object element = list.get(i);
                    String newPath = path.isEmpty() ? fieldName + "[" + i + "]" : path + "." + fieldName + "[" + i + "]";
                    resolvePath(flatMap, tokens, index + 1, element, newPath);
                }
            } else {
                if (index == tokens.length - 1) {
                    String finalKey = path.isEmpty() ? token : path + "." + token;
                    flatMap.put(finalKey, value != null ? value.toString() : "");
                } else {
                    String newPath = path.isEmpty() ? fieldName : path + "." + fieldName;
                    resolvePath(flatMap, tokens, index + 1, value, newPath);
                }
            }

        } catch (Exception e) {
            String errorKey = path.isEmpty() ? token : path + "." + token;
            flatMap.put(errorKey, "ERROR");
        }
    }
}
