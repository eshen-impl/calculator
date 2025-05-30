package org.ebay.assignment.strategy;

import org.ebay.assignment.model.Operation;

import java.math.BigDecimal;
import java.math.MathContext;

public interface OperationStrategy {
    Operation getOperation();
    BigDecimal apply(BigDecimal a, BigDecimal b, MathContext context);
}
