package com.example.helper;

import com.example.model.Location;
import java.util.*;

public class LocationHelper {

    public static Map<String, String> flatten(List<Location> locations, int maxCount, Properties props) {
        Map<String, String> result = new LinkedHashMap<>();
        for (int i = 0; i < maxCount; i++) {
            if (i < locations.size()) {
                Location loc = locations.get(i);
                String base = "address.locations[*]";
                if (shouldInclude(base + ".type", props))
                    result.put(getHeaderName(base + ".type", props) + "[" + i + "]", loc.getType());
                if (shouldInclude(base + ".zip", props))
                    result.put(getHeaderName(base + ".zip", props) + "[" + i + "]", loc.getZip());
            } else {
                String base = "address.locations[*]";
                if (shouldInclude(base + ".type", props))
                    result.put(getHeaderName(base + ".type", props) + "[" + i + "]", "");
                if (shouldInclude(base + ".zip", props))
                    result.put(getHeaderName(base + ".zip", props) + "[" + i + "]", "");
            }
        }
        return result;
    }

    private static boolean shouldInclude(String field, Properties props) {
        return props.containsKey(field) && props.getProperty(field).startsWith("Y");
    }

    private static String getHeaderName(String field, Properties props) {
        return props.containsKey(field) && props.getProperty(field).contains(":")
            ? props.getProperty(field).split(":", 2)[1]
            : field;
    }
}