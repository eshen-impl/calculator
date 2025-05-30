package org.ebay.assignment.controller;

import jakarta.validation.Valid;
import org.ebay.assignment.model.CalculateRequest;
import org.ebay.assignment.model.ChainRequest;
import org.ebay.assignment.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;


@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService service) {
        this.calculatorService = service;
    }

    @PostMapping("/calculate")
    public ResponseEntity<BigDecimal> calculate(@Valid @RequestBody CalculateRequest request) {
        MathContext context = new MathContext(request.getPrecision(), request.getRoundingMode());
        BigDecimal result = calculatorService.calculate(request.getOperation(),
                request.getLeft(), request.getRight(), context);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/chain")
    public ResponseEntity<BigDecimal> chainCalculation(@Valid @RequestBody ChainRequest request) {
        MathContext context = new MathContext(request.getPrecision(), request.getRoundingMode());
        BigDecimal result = calculatorService.chain(request.getInitial(), request.getSteps(), context);
        return ResponseEntity.ok(result);
    }


}
