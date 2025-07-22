package com.example.wrapper;

import com.example.model.Person;
import com.example.model.Address;
import com.example.model.Location;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class PersonWrapperTest {
    @Test
    public void testDelegationAndOverride() {
        Location loc = new Location();
        loc.setType("home");
        loc.setZip("10001");
        loc.setCoords(Arrays.asList("11", "22"));

        Address addr = new Address();
        addr.setCity("Wonderland");
        addr.setZip("12345");
        addr.setLocations(Arrays.asList(loc));

        Person person = new Person();
        person.setId(1);
        person.setName("Alice");
        person.setAddress(addr);

        PersonWrapper wrapper = new PersonWrapper(person);
        assertEquals("[User] Alice", wrapper.getName());
        assertEquals("Wonderland", wrapper.getAddress().getCity());
        assertEquals("home", wrapper.getAddress().getLocations().get(0).getType());
    }
}
