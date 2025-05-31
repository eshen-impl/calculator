# Flexible Calculator API

This project is a Spring Boot-based flexible calculator that supports basic arithmetic operations.

## Features

- Basic operations: `ADD`, `SUBTRACT`, `MULTIPLY`, `DIVIDE`
- Chaining operations with precision and rounding control
- Easily extensible via Strategy pattern
- Open for new operations without modifying the core logic
- Input validation and global error handling
- Unit tested with edge case coverage
- Compatible with IoC/DI frameworks (Spring Boot)

---

## API Endpoints

### 1. Basic Calculation
POST /api/v1/calculator/calculate

#### Request Parameters (sample case):
```json
{
  "operation": "ADD",
  "left": 10,
  "right": 5,
  "precision": 10,
  "roundingMode": "HALF_UP"
}
```

### 2. Chained Calculation


POST /api/v1/calculator/calculate/chain


#### Request Body (sample case):

```json
{
  "initialValue": 5,
  "steps": [
    { "operation": "ADD", "operand": 3 },
    { "operation": "MULTIPLY", "operand": 2 }
  ],
  "precision": 10,
  "roundingMode": "HALF_UP"
}
```

## Design Decisions & Assumptions

### Object-Oriented Principles

- Applied **Open-Closed Principle**: New operations can be added without modifying `CalculatorServiceImpl`.
- Used the **Strategy Pattern** for operation handling.
- Operations are represented by an enum (`Operation`) and corresponding strategy classes.

### IoC & Extensibility

- All strategies implement `OperationStrategy` and are registered as Spring `@Component`.
- The list of available strategies is injected via Spring's DI mechanism (`List<OperationStrategy>`).

### Input Handling & Precision

- All calculations use `BigDecimal` to ensure precision.
- Default precision is set to `10` and rounding mode to `HALF_UP`.
- Users can customize `precision` and `roundingMode` via request parameters.

### Exception Handling

- Gracefully handles:
  - Division by zero (`ArithmeticException`)
  - Malformed JSON or invalid enums (`HttpMessageNotReadableException`)
  - Validation errors (`MethodArgumentNotValidException`)
  - Unsupported operations (`UnsupportedOperationException`)
- Returns standardized error responses with appropriate HTTP status codes.

### Validation

- Request DTOs use `javax.validation.constraints` (e.g., `@NotNull`, `@Positive`).
- Global exception handler provides meaningful messages with field names and rejected values.

## Testing

- Unit tests for all operations using `@SpringBootTest`
- Coverage includes normal and edge cases (e.g., long numbers, division by zero)

## Getting Started

### Prerequisites

- Java 17+
- Spring Boot 3.x

The application will start on port `8080`