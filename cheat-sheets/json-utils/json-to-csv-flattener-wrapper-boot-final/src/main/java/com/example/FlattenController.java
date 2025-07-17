package com.example;

import com.example.model.Person;
import com.example.util.CSVFlattener;
import com.example.wrapper.PersonWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

@RestController
public class FlattenController {

    @Value("${fields.prop:flat-fields.properties}")
    private String fieldsProp;

    @PostMapping("/flatten")
    public Map<String, String> flatten(@RequestBody String json) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(fieldsProp));

        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(json, Person.class);
        PersonWrapper wrapper = new PersonWrapper(person);

        CSVFlattener flattener = new CSVFlattener(props);
        return flattener.flatten(wrapper);
    }
}
