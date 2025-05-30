package org.ebay.assignment.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChainRequest {

    @NotNull
    private BigDecimal initial;

    @Size(min = 1, message = "At least one calculation step is required")
    @Valid
    private List<@Valid CalculationStep> steps;

    @Positive
    private int precision = 10;

    @NotNull
    private RoundingMode roundingMode = RoundingMode.HALF_UP;
}
