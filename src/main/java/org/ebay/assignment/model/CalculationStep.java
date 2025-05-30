package org.ebay.assignment.model;

import java.math.BigDecimal;

public record CalculationStep(Operation operation, BigDecimal operand) {
}
