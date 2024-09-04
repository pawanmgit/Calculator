import static org.junit.jupiter.api.Assertions.*;

import org.example.Calculator;
import org.example.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    // Test for addition
    @Test
    public void testAddition() {
        assertEquals(8.0, calculator.calculate(Operation.ADD, 5, 3));
    }

    // Test for subtraction
    @Test
    public void testSubtraction() {
        assertEquals(2.0, calculator.calculate(Operation.SUBTRACT, 5, 3));
    }

    // Test for multiplication
    @Test
    public void testMultiplication() {
        assertEquals(15.0, calculator.calculate(Operation.MULTIPLY, 5, 3));
    }

    // Test for division
    @Test
    public void testDivision() {
        assertEquals(3.0, calculator.calculate(Operation.DIVIDE, 6, 2));
    }

    // Test for division by zero (expecting an exception)
    @Test
    public void testDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(Operation.DIVIDE, 5, 0);
        });
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    // Test for chaining multiple operations
    @Test
    public void testChainingOperations() {
        Operation[] ops = {Operation.ADD, Operation.MULTIPLY};
        double[] vals = {3, 2};
        assertEquals(16.0, calculator.chainCalculate(5, ops, vals));
    }

    // Test for unsupported operations
    @Test
    public void testUnsupportedOperation() {
        // Test a custom operation that isn't supported (e.g., POWER)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(Operation.valueOf("POWER"), 5, 3);
        });

        assertEquals("No enum constant org.example.Operation.POWER", exception.getMessage());
    }

}
