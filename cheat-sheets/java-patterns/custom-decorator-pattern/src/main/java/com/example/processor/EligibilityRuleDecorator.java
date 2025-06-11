package com.example.processor;

import com.example.model.MyData;

public class EligibilityRuleDecorator implements MyDataProcessor {
    private MyDataProcessor next;

    public EligibilityRuleDecorator(MyDataProcessor next) {
        this.next = next;
    }

    public void process(MyData data) {
        next.process(data);
        data.isEligibleForLoan = data.age >= 21 && data.age <= 65;
    }
}
