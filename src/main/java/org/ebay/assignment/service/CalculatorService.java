package org.ebay.assignment.service;

import org.ebay.assignment.model.CalculationStep;
import org.ebay.assignment.model.Operation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public interface CalculatorService {

    BigDecimal calculate(Operation operation, BigDecimal a, BigDecimal b, MathContext context);

    BigDecimal chain(BigDecimal initialValue, List<CalculationStep> steps, MathContext context);
}
