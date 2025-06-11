package com.example.parser;

import com.example.model.MyData;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class MyDataParser {
    public static MyData parse(File file) throws Exception {
        MyData data = new MyData();
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(file);
        Element root = doc.getDocumentElement();
        data.name = root.getElementsByTagName("name").item(0).getTextContent();
        data.age = Integer.parseInt(root.getElementsByTagName("age").item(0).getTextContent());
        return data;
    }
}
