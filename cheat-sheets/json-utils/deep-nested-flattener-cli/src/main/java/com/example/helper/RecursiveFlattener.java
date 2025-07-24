package com.example.helper;

import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecursiveFlattener {

    private static final String INDEX_PLACEHOLDER = "[*]";
    private static final Pattern ARRAY_PATH_PATTERN = Pattern.compile("\\[(\\d+)]");

    public static List<Map<String, String>> flattenList(List<?> dataList, Properties fieldProps) {
        Map<String, String> outputFieldLabels = new LinkedHashMap<>();
        for (String propKey : fieldProps.stringPropertyNames()) {
            String value = fieldProps.getProperty(propKey);
            if (value.startsWith("Y:")) {
                outputFieldLabels.put(propKey, value.substring(2));
            } else if (value.equalsIgnoreCase("Y")) {
                outputFieldLabels.put(propKey, propKey);
            }
        }

        Map<String, Integer> maxSizes = new HashMap<>();
        for (Object obj : dataList) {
            calculateMaxArraySizes(obj, "", maxSizes);
        }

        List<Map<String, String>> allRows = new ArrayList<>();
        for (Object obj : dataList) {
            List<Map<String, String>> rows = new ArrayList<>();
            flattenRecursive(obj, "", new LinkedHashMap<>(), outputFieldLabels, maxSizes, rows);
            allRows.addAll(rows);
        }

        return allRows;
    }

    private static void calculateMaxArraySizes(Object obj, String path, Map<String, Integer> maxSizes) {
        if (obj == null) return;
        Class<?> clazz = obj.getClass();

        if (isPrimitiveOrWrapper(clazz) || obj instanceof String) return;

        if (obj instanceof Collection<?>) {
            Collection<?> list = (Collection<?>) obj;
            maxSizes.put(path, Math.max(maxSizes.getOrDefault(path, 0), list.size()));
            int index = 0;
            for (Object item : list) {
                calculateMaxArraySizes(item, path + "[" + index + "]", maxSizes);
                index++;
            }
            return;
        }

        for (Field field : clazz.getFields()) {
            try {
                Object value = field.get(obj);
                String newPath = path.isEmpty() ? field.getName() : path + "." + field.getName();
                calculateMaxArraySizes(value, newPath, maxSizes);
            } catch (Exception ignored) {}
        }
    }

    private static void flattenRecursive(Object obj,
                                         String path,
                                         Map<String, String> currentRow,
                                         Map<String, String> outputFields,
                                         Map<String, Integer> maxSizes,
                                         List<Map<String, String>> rows) {
        if (obj == null) return;
        Class<?> clazz = obj.getClass();

        if (isPrimitiveOrWrapper(clazz) || obj instanceof String) {
            for (String outputField : outputFields.keySet()) {
                String regex = path.replaceAll("\\[\\d+\\]", INDEX_PLACEHOLDER);
                if (outputField.equals(regex)) {
                    String colLabel = generateColumnLabel(outputFields.get(outputField), path);
                    currentRow.put(colLabel, obj.toString());
                }
            }
            return;
        }

        if (obj instanceof Collection<?>) {
            Collection<?> list = (Collection<?>) obj;
            int max = maxSizes.getOrDefault(path, list.size());
            int index = 0;
            for (Object item : list) {
                flattenRecursive(item, path + "[" + index + "]", currentRow, outputFields, maxSizes, rows);
                index++;
            }
            while (index < max) {
                fillNaN(obj, path + "[" + index + "]", currentRow, outputFields);
                index++;
            }
            return;
        }

        for (Field field : clazz.getFields()) {
            try {
                Object value = field.get(obj);
                String newPath = path.isEmpty() ? field.getName() : path + "." + field.getName();
                flattenRecursive(value, newPath, currentRow, outputFields, maxSizes, rows);
            } catch (Exception ignored) {}
        }

        if (path.isEmpty()) {
            rows.add(new LinkedHashMap<>(currentRow));
        }
    }

    private static void fillNaN(Object sampleObj,
                                String path,
                                Map<String, String> currentRow,
                                Map<String, String> outputFields) {
        for (String outputField : outputFields.keySet()) {
            String regex = path.replaceAll("\\[\\d+\\]", INDEX_PLACEHOLDER);
            if (outputField.equals(regex)) {
                String colLabel = generateColumnLabel(outputFields.get(outputField), path);
                currentRow.put(colLabel, "NaN");
            }
        }
    }

    private static String generateColumnLabel(String label, String fullPath) {
        Matcher matcher = ARRAY_PATH_PATTERN.matcher(fullPath);
        StringBuilder sb = new StringBuilder(label);
        while (matcher.find()) {
            sb.append("__idx").append(matcher.group(1));  // safer than underscores in keys
        }
        return sb.toString();
    }

    private static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() ||
               clazz == Integer.class ||
               clazz == Long.class ||
               clazz == Boolean.class ||
               clazz == Byte.class ||
               clazz == Short.class ||
               clazz == Double.class ||
               clazz == Float.class ||
               clazz == Character.class;
    }
}
