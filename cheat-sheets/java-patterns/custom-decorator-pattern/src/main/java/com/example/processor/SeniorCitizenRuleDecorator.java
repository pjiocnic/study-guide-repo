package com.example.processor;

import com.example.model.MyData;

public class SeniorCitizenRuleDecorator implements MyDataProcessor {
    private MyDataProcessor next;

    public SeniorCitizenRuleDecorator(MyDataProcessor next) {
        this.next = next;
    }

    public void process(MyData data) {
        next.process(data);
        data.isSeniorCitizen = data.age >= 60;
    }
}
