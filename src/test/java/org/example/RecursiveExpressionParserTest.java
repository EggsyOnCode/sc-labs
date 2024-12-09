package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecursiveExpressionParserTest {

    @Test
    public void testSimpleExpressions() {
        assertEquals(7.0, RecursiveExpressionParser.evaluateExpression("3 + 4"), 0.0001);
        assertEquals(10.0, RecursiveExpressionParser.evaluateExpression("5 * 2"), 0.0001);
        assertEquals(2.5, RecursiveExpressionParser.evaluateExpression("5 / 2"), 0.0001);
    }

    @Test
    public void testComplexExpressions() {
        assertEquals(14.0, RecursiveExpressionParser.evaluateExpression("2 + 3 * 4"), 0.0001);
        assertEquals(10.0, RecursiveExpressionParser.evaluateExpression("(2 + 3) * 2"), 0.0001);
    }

    @Test
    public void testFloatingPointNumbers() {
        assertEquals(5.75, RecursiveExpressionParser.evaluateExpression("2.5 + 3.25"), 0.0001);
        assertEquals(8.64, RecursiveExpressionParser.evaluateExpression("2.4 * 3.6"), 0.0001);
    }

    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> RecursiveExpressionParser.evaluateExpression("5 / 0"));
    }

    @Test
    public void testInvalidExpressions() {
        assertThrows(IllegalArgumentException.class, () -> RecursiveExpressionParser.evaluateExpression(""));
        assertThrows(IllegalArgumentException.class, () -> RecursiveExpressionParser.evaluateExpression(null));
        assertThrows(IllegalArgumentException.class, () -> RecursiveExpressionParser.evaluateExpression("3 + abc"));
    }
}
