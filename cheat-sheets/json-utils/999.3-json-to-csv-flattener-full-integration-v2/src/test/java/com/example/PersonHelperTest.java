package com.example;

import com.example.model.*;
import com.example.helper.RecursiveFlattener;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PersonHelperTest {

    @Test
    public void testRecursiveFlattenNestedPerson() {
        Person p = new Person();
        p.id = 101;
        p.name = "Alice";

        Address addr = new Address();
        addr.city = "Wonderland";
        addr.zip = "12345";

        Location loc1 = new Location();
        loc1.type = "home";
        loc1.zip = "1000";

        Location loc2 = new Location();
        loc2.type = "office";
        loc2.zip = "2000";

        addr.locations = Arrays.asList(loc1, loc2);
        p.address = addr;

        Properties props = new Properties();
        props.setProperty("id", "Y:ID");
        props.setProperty("name", "Y:Name");
        props.setProperty("address.city", "Y:City");
        props.setProperty("address.locations[0].type", "Y:Type0");
        props.setProperty("address.locations[1].type", "Y:Type1");

        Map<String, String> result = RecursiveFlattener.flatten(p, "", props, 10);

        assertEquals("Alice", result.get("Name"));
        assertEquals("Wonderland", result.get("City"));
        assertEquals("home", result.get("Type0"));
        assertEquals("office", result.get("Type1"));
    }
}