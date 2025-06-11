package com.example.processor;

import com.example.model.MyData;

public class TaxBracketRuleDecorator implements MyDataProcessor {
    private MyDataProcessor next;

    public TaxBracketRuleDecorator(MyDataProcessor next) {
        this.next = next;
    }

    public void process(MyData data) {
        next.process(data);
        if (data.age < 18) data.taxBracket = "N/A";
        else if (data.age < 60) data.taxBracket = "Standard";
        else data.taxBracket = "Senior Discount";
    }
}
