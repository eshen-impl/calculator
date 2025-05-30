package org.ebay.assignment.service.impl;

import org.ebay.assignment.model.CalculationStep;
import org.ebay.assignment.model.Operation;
import org.ebay.assignment.service.CalculatorService;
import org.ebay.assignment.strategy.OperationStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private final Map<Operation, OperationStrategy> operationStrategies;

    public CalculatorServiceImpl(List<OperationStrategy> strategies) {
        this.operationStrategies = strategies.stream()
                .collect(Collectors.toMap(
                        OperationStrategy::getOperation,
                        Function.identity()
                ));
    }

    public BigDecimal calculate(Operation operation, BigDecimal a, BigDecimal b, MathContext context) {
        OperationStrategy strategy = operationStrategies.get(operation);
        if (strategy == null) {
            throw new UnsupportedOperationException("Unsupported operation: " + operation);
        }
        return strategy.apply(a, b, context);
    }

    public BigDecimal chain(BigDecimal initialValue, List<CalculationStep> steps, MathContext context) {
        BigDecimal result = initialValue;
        for (CalculationStep step : steps) {
            OperationStrategy strategy = operationStrategies.get(step.operation());
            if (strategy == null) {
                throw new UnsupportedOperationException("Unsupported operation in chain: " + step.operation());
            }
            result = strategy.apply(result, step.operand(), context);
        }
        return result;
    }
}
