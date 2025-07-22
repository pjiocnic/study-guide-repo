package com.example.util;

import com.example.wrapper.IPersonWrapper;

import java.util.*;

public class CSVFlattener {
    private final Properties fieldMap;

    public CSVFlattener(Properties fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, String> flatten(IPersonWrapper person) {
        Map<String, String> flat = new LinkedHashMap<>();
        flat.put("id", String.valueOf(person.getId()));
        flat.put("name", person.getName());
        flat.put("displayName", person.getDisplayName());

        if (person.getAddress() != null) {
            flat.put("address.city", person.getAddress().getCity());
            flat.put("address.zip", person.getAddress().getZip());
            if (person.getAddress().getLocations() != null) {
                int i = 0;
                for (var loc : person.getAddress().getLocations()) {
                    flat.put("address.locations[" + i + "].type", loc.getType());
                    flat.put("address.locations[" + i + "].zip", loc.getZip());
                    if (loc.getCoords() != null) {
                        for (int j = 0; j < loc.getCoords().size(); j++) {
                            flat.put("address.locations[" + i + "].coords[" + j + "]", loc.getCoords().get(j));
                        }
                    }
                    i++;
                }
            }
        }
        return flat;
    }
}
