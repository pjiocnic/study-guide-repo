package com.example.processor;

import com.example.model.MyData;
import java.util.*;

public class DecoratorFactory {
    public static MyDataProcessor buildProcessorChain(List<String> decorators) {
        MyDataProcessor processor = new BaseProcessor();
        ListIterator<String> it = decorators.listIterator(decorators.size());
        while (it.hasPrevious()) {
            String decorator = it.previous().trim().toLowerCase();
            switch (decorator) {
                case "senior": processor = new SeniorCitizenRuleDecorator(processor); break;
                case "category": processor = new CategoryRuleDecorator(processor); break;
                case "tax": processor = new TaxBracketRuleDecorator(processor); break;
                case "eligibility": processor = new EligibilityRuleDecorator(processor); break;
                default: throw new IllegalArgumentException("Unknown decorator: " + decorator);
            }
        }
        return processor;
    }
}
