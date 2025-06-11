package com.example.processor;

import com.example.model.MyData;

public class CategoryRuleDecorator implements MyDataProcessor {
    private MyDataProcessor next;

    public CategoryRuleDecorator(MyDataProcessor next) {
        this.next = next;
    }

    public void process(MyData data) {
        next.process(data);
        if (data.age < 18) data.category = "Minor";
        else if (data.age < 60) data.category = "Adult";
        else data.category = "Senior";
    }
}
