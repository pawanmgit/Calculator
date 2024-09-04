package org.example;

import java.util.HashMap;
import java.util.Map;

// Calculator class with IoC support and chaining operations
public class Calculator {

    // Store the operations in a map for easy extensibility
    private final Map<Operation, CalculatorOperation> operations = new HashMap<>();

    // Constructor to inject default operations
    public Calculator() {
        operations.put(Operation.ADD, (num1, num2) -> num1 + num2);
        operations.put(Operation.SUBTRACT, (num1, num2) -> num1 - num2);
        operations.put(Operation.MULTIPLY, (num1, num2) -> num1 * num2);
        operations.put(Operation.DIVIDE, (num1, num2) -> {
            if (num2 == 0) throw new ArithmeticException("Cannot divide by zero");
            return num1 / num2;
        });
    }

    // Extensible method for adding operations dynamically
    public void addOperation(Operation op, CalculatorOperation operation) {
        operations.put(op, operation);
    }

    // Basic calculation method
    public double calculate(Operation op, double num1, double num2) {
        if (!operations.containsKey(op)) {
            throw new UnsupportedOperationException("Operation not supported: " + op);
        }
        return operations.get(op).apply(num1, num2);
    }

    // Chaining operations method
    public double chainCalculate(double initialValue, Operation[] operations, double[] values) {
        double result = initialValue;
        for (int i = 0; i < operations.length; i++) {
            result = calculate(operations[i], result, values[i]);
        }
        return result;
    }
}



