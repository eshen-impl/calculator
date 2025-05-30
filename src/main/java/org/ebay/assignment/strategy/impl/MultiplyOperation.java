package org.ebay.assignment.strategy.impl;


import org.ebay.assignment.model.Operation;
import org.ebay.assignment.strategy.OperationStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class MultiplyOperation implements OperationStrategy {

    @Override
    public Operation getOperation() {
        return Operation.MULTIPLY;
    }

    @Override
    public BigDecimal apply(BigDecimal a, BigDecimal b, MathContext context) {
        return a.multiply(b, context);
    }
}
