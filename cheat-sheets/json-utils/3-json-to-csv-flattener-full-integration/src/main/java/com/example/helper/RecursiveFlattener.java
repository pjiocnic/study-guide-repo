package com.example.helper;

import java.lang.reflect.*;
import java.util.*;

public class RecursiveFlattener {

    public static Map<String, String> flatten(Object obj, String pathPrefix, Properties props, int depthLimit) {
        Map<String, String> flatMap = new LinkedHashMap<>();
        if (obj == null || depthLimit <= 0) return flatMap;

        Class<?> clazz = obj.getClass();

        if (isPrimitiveOrWrapper(clazz) || obj instanceof String) {
            if (shouldInclude(pathPrefix, props))
                flatMap.put(getHeaderName(pathPrefix, props), obj.toString());
            return flatMap;
        }

        if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            for (int i = 0; i < list.size(); i++) {
                Object child = list.get(i);
                String newPath = pathPrefix + "[" + i + "]";
                flatMap.putAll(flatten(child, newPath, props, depthLimit - 1));
            }
            return flatMap;
        }

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                String newPrefix = pathPrefix.isEmpty() ? field.getName() : pathPrefix + "." + field.getName();
                flatMap.putAll(flatten(value, newPrefix, props, depthLimit - 1));
            } catch (IllegalAccessException ignored) {}
        }

        return flatMap;
    }

    private static boolean shouldInclude(String field, Properties props) {
        if (!props.containsKey(field)) return false;
        return props.getProperty(field).startsWith("Y");
    }

    private static String getHeaderName(String field, Properties props) {
        return props.containsKey(field) && props.getProperty(field).contains(":")
            ? props.getProperty(field).split(":", 2)[1]
            : field;
    }

    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() || type == Boolean.class || type == Integer.class ||
               type == Long.class || type == Double.class || type == Float.class ||
               type == Short.class || type == Byte.class || type == Character.class;
    }
}