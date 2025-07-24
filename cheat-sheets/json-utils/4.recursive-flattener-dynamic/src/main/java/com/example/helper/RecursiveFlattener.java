package com.example.helper;

import java.lang.reflect.*;
import java.util.*;

public class RecursiveFlattener {

    public static List<Map<String, String>> flattenList(List<?> topLevelList, Properties props) {
        // Determine max array sizes per path
        Map<String, Integer> maxArraySizes = new HashMap<>();
        for (Object obj : topLevelList) {
            computeMaxArrayDepths(obj, "", maxArraySizes, 10);
        }

        List<Map<String, String>> allRows = new ArrayList<>();
        for (Object obj : topLevelList) {
            Map<String, String> row = new LinkedHashMap<>();
            flattenObject(obj, "", props, maxArraySizes, row, 10);
            allRows.add(row);
        }
        return allRows;
    }

    private static void computeMaxArrayDepths(Object obj, String path, Map<String, Integer> maxSizes, int depth) {
        if (obj == null || depth <= 0) return;
        if (obj instanceof List<?>) {
            List<?> list = (List<?>) obj;
            maxSizes.put(path, Math.max(maxSizes.getOrDefault(path, 0), list.size()));
            for (int i = 0; i < list.size(); i++) {
                computeMaxArrayDepths(list.get(i), path + "[*]", maxSizes, depth - 1);
            }
        } else if (!isPrimitiveOrWrapper(obj.getClass()) && !(obj instanceof String)) {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object child = field.get(obj);
                    String newPath = path.isEmpty() ? field.getName() : path + "." + field.getName();
                    computeMaxArrayDepths(child, newPath, maxSizes, depth - 1);
                } catch (Exception ignored) {}
            }
        }
    }

    private static void flattenObject(Object obj, String path, Properties props, Map<String, Integer> maxSizes,
                                      Map<String, String> output, int depth) {
        if (depth <= 0) return;

        if (obj instanceof List<?>) {
            List<?> list = (List<?>) obj;
            int max = maxSizes.getOrDefault(path, list.size());
            for (int i = 0; i < max; i++) {
                Object child = i < list.size() ? list.get(i) : null;
                String nextPath = path + "[*]";
                flattenObject(child, nextPath, props, maxSizes, output, depth - 1);
            }
            return;
        }

        if (obj == null || isPrimitiveOrWrapper(obj.getClass()) || obj instanceof String) {
            if (props.containsKey(path)) {
                String header = getHeaderName(path, props);
                output.put(header, obj == null ? "NaN" : obj.toString());
            }
            return;
        }

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object child = field.get(obj);
                String newPath = path.isEmpty() ? field.getName() : path + "." + field.getName();
                flattenObject(child, newPath, props, maxSizes, output, depth - 1);
            } catch (Exception ignored) {}
        }
    }

    private static String getHeaderName(String path, Properties props) {
        if (props.containsKey(path)) {
            String val = props.getProperty(path);
            if (val.contains(":")) return val.split(":", 2)[1];
            return val;
        }
        return path;
    }

    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() ||
               type == Boolean.class || type == Integer.class || type == Long.class ||
               type == Double.class || type == Float.class || type == Short.class ||
               type == Byte.class || type == Character.class;
    }
}