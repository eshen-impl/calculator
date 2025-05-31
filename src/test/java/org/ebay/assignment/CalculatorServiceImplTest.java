package org.ebay.assignment;

import org.ebay.assignment.model.CalculationStep;
import org.ebay.assignment.model.Operation;
import org.ebay.assignment.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CalculatorServiceImplTest {

    @Autowired
    private CalculatorService calculatorService;

    private MathContext context = new MathContext(10, RoundingMode.HALF_UP);

    @Test
    void testAddition() {
        BigDecimal result = calculatorService.calculate(Operation.ADD,
                new BigDecimal("10.5"),
                new BigDecimal("2.3"),
                context);
        assertEquals(new BigDecimal("12.8"), result.stripTrailingZeros());
    }

    @Test
    void testSubtraction() {
        BigDecimal result = calculatorService.calculate(
                Operation.SUBTRACT,
                new BigDecimal("100"),
                new BigDecimal("35.5"),
                context
        );
        assertEquals(new BigDecimal("64.5"), result.stripTrailingZeros());
    }

    @Test
    void testMultiplication() {
        BigDecimal result = calculatorService.calculate(
                Operation.MULTIPLY,
                new BigDecimal("6"),
                new BigDecimal("7.5"),
                context
        );
        assertEquals(new BigDecimal("45"), result.stripTrailingZeros());
    }

    @Test
    void testDivision() {
        BigDecimal result = calculatorService.calculate(
                Operation.DIVIDE,
                new BigDecimal("22"),
                new BigDecimal("7"),
                context
        );
        assertEquals(new BigDecimal("3.142857143"), result); // Based on precision = 10
    }

    @Test
    void testDivisionByZero() {
        assertThrows(
                ArithmeticException.class,
                () -> calculatorService.calculate(Operation.DIVIDE, new BigDecimal("10"),
                        BigDecimal.ZERO, context)
        );
    }


    @Test
    void testLongNumberPrecision() {
        BigDecimal longNum1 = new BigDecimal("12345678901234567890");
        BigDecimal longNum2 = new BigDecimal("98765432109876543210");

        BigDecimal result = calculatorService.calculate(Operation.ADD, longNum1, longNum2, context);
        assertEquals(new BigDecimal("1.11111111E+20"), result.stripTrailingZeros());
    }

    @Test
    void testChainOperations() {
        BigDecimal initialValue = new BigDecimal("5");
        List<CalculationStep> steps = List.of(
                new CalculationStep(Operation.ADD, new BigDecimal("3")),
                new CalculationStep(Operation.MULTIPLY, new BigDecimal("2")),
                new CalculationStep(Operation.SUBTRACT, new BigDecimal("4"))
        );

        BigDecimal result = calculatorService.chain(initialValue, steps, context);
        assertEquals(new BigDecimal("12"), result.stripTrailingZeros());
    }

    @Test
    void testDivideWithRounding() {
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("3");

        BigDecimal result = calculatorService.calculate(Operation.DIVIDE, a, b, context);
        assertEquals(new BigDecimal("3.333333333"), result);
    }

    @Test
    void testUnsupportedOperation() {
        assertThrows(UnsupportedOperationException.class, () -> {
            calculatorService.calculate(null, BigDecimal.ONE, BigDecimal.ONE, context);
        });

    }
}

