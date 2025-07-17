package com.example.wrapper;

import com.example.model.Person;
import com.example.model.Address;
import com.example.model.Location;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class PersonWrapperTest {

    @Test
    public void testWrapperDelegationAndExtension() {
        Location loc = new Location();
        loc.setType("home");
        loc.setZip("1234");
        loc.setCoords(Arrays.asList("11", "22"));

        Address addr = new Address();
        addr.setCity("Wonderland");
        addr.setZip("45678");
        addr.setLocations(Arrays.asList(loc));

        Person person = new Person();
        person.setId(101);
        person.setName("Alice");
        person.setAddress(addr);

        PersonWrapper wrapper = new PersonWrapper(person);

        assertEquals("Alice", wrapper.getName());
        assertEquals("[User] Alice", wrapper.getDisplayName());
        assertEquals("Wonderland", wrapper.getAddress().getCity());
        assertEquals("home", wrapper.getAddress().getLocations().get(0).getType());
    }
}
