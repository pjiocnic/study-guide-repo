package com.example;

import com.example.model.MyData;
import com.example.parser.MyDataParser;
import com.example.processor.*;

import java.io.File;
import java.io.InputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        File xmlFile = new File(Main.class.getClassLoader().getResource("data.xml").getFile());
        MyData data = MyDataParser.parse(xmlFile);

        Properties props = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        }

        String decoratorList = props.getProperty("decorators");
        List<String> decorators = Arrays.asList(decoratorList.split(","));
        MyDataProcessor processor = DecoratorFactory.buildProcessorChain(decorators);
        processor.process(data);

        System.out.println(data);
    }
}
