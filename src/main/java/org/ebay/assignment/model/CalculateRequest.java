package org.ebay.assignment.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateRequest {

    @NotNull(message = "Operation must not be null")
    private Operation operation;

    @NotNull(message = "Left operand must not be null")
    private BigDecimal left;

    @NotNull(message = "Right operand must not be null")
    private BigDecimal right;

    @Positive
    private int precision = 10;

    @NotNull
    private RoundingMode roundingMode = RoundingMode.HALF_UP;
}
