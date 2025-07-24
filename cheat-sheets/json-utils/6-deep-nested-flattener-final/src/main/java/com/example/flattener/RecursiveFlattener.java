package com.example.flattener;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.*;
import java.util.*;

public class RecursiveFlattener {
    private Map<String, Integer> maxArraySizes = new HashMap<>();

    public List<Map<String, String>> flattenObjects(List<Object> objects, Properties props) {
        List<Map<String, String>> allRows = new ArrayList<>();
        for (Object obj : objects) {
            Map<String, String> flatMap = new LinkedHashMap<>();
            flatten("", obj, flatMap);
            allRows.add(flatMap);
        }
        return normalize(allRows);
    }

    private void flatten(String path, Object obj, Map<String, String> flatMap) {
        if (obj == null) return;

        Class<?> clazz = obj.getClass();
        if (isPrimitive(clazz)) {
            flatMap.put(path, obj.toString());
        } else if (obj instanceof Collection) {
            Collection<?> coll = (Collection<?>) obj;
            int i = 0;
            for (Object item : coll) {
                flatten(path + "[" + i + "]", item, flatMap);
                i++;
            }
            maxArraySizes.put(path, Math.max(maxArraySizes.getOrDefault(path, 0), coll.size()));
        } else {
            for (Method m : clazz.getMethods()) {
                if (m.getName().startsWith("get") && m.getParameterCount() == 0) {
                    try {
                        String name = decapitalize(m.getName().substring(3));
                        Object value = m.invoke(obj);
                        String newPath = path.isEmpty() ? name : path + "." + name;
                        flatten(newPath, value, flatMap);
                    } catch (Exception ignored) {}
                }
                if (m.getName().startsWith("is") && m.getParameterCount() == 0 && m.getReturnType() == boolean.class) {
                    try {
                        String name = decapitalize(m.getName().substring(2));
                        Object value = m.invoke(obj);
                        String newPath = path.isEmpty() ? name : path + "." + name;
                        flatten(newPath, value, flatMap);
                    } catch (Exception ignored) {}
                }
            }
        }
    }

    private List<Map<String, String>> normalize(List<Map<String, String>> rows) {
        Set<String> allKeys = new LinkedHashSet<>();
        for (Map<String, String> row : rows) allKeys.addAll(row.keySet());

        List<Map<String, String>> normalized = new ArrayList<>();
        for (Map<String, String> row : rows) {
            Map<String, String> norm = new LinkedHashMap<>();
            for (String key : allKeys) {
                norm.put(key, row.getOrDefault(key, "NaN"));
            }
            normalized.add(norm);
        }
        return normalized;
    }

    private boolean isPrimitive(Class<?> type) {
        return type.isPrimitive() || type == String.class ||
               type == Integer.class || type == Double.class ||
               type == Boolean.class || type == Long.class ||
               type == Float.class || type == Short.class;
    }

    private String decapitalize(String s) {
        return s == null || s.isEmpty() ? s : Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }
}
