package org.ebay.assignment.strategy.impl;

import org.ebay.assignment.model.Operation;
import org.ebay.assignment.strategy.OperationStrategy;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.MathContext;

@Component
public class DivideOperation implements OperationStrategy {

    @Override
    public Operation getOperation() {
        return Operation.DIVIDE;
    }

    @Override
    public BigDecimal apply(BigDecimal a, BigDecimal b, MathContext context) {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a.divide(b, context);
    }
}
