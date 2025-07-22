package com.example;

import com.example.model.*;
import com.example.helper.LocationHelper;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LocationHelperTest {

    @Test
    public void testFlattenLocation_WithPaddingAndProperties() {
        List<Location> locations = new ArrayList<>();
        Location loc1 = new Location();
        loc1.type = "home";
        loc1.zip = "12345";
        Location loc2 = new Location();
        loc2.type = "office";
        loc2.zip = "67890";
        locations.add(loc1);
        locations.add(loc2);

        Properties props = new Properties();
        props.setProperty("address.locations[*].type", "Y:Type");
        props.setProperty("address.locations[*].zip", "N");

        Map<String, String> flat = LocationHelper.flatten(locations, 3, props);

        assertEquals(3, flat.size()); // only "type" keys, for index 0 to 2
        assertEquals("home", flat.get("Type[0]"));
        assertEquals("office", flat.get("Type[1]"));
        assertEquals("", flat.get("Type[2]")); // padded
    }
}