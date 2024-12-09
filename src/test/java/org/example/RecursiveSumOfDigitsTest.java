package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecursiveSumOfDigitsTest {

    @Test
    public void testZero() {
        // Edge case: number is 0
        assertEquals(0, RecursiveSumOfDigits.sumOfDigits(0));
    }

    @Test
    public void testSingleDigitNumber() {
        // Single-digit numbers
        assertEquals(5, RecursiveSumOfDigits.sumOfDigits(5));
        assertEquals(8, RecursiveSumOfDigits.sumOfDigits(8));
    }

    @Test
    public void testMultiDigitNumber() {
        // Multi-digit numbers
        assertEquals(6, RecursiveSumOfDigits.sumOfDigits(123)); // 1 + 2 + 3 = 6
        assertEquals(15, RecursiveSumOfDigits.sumOfDigits(456)); // 4 + 5 + 6 = 15
        assertEquals(10, RecursiveSumOfDigits.sumOfDigits(19)); // 1 + 9 = 10
    }

    @Test
    public void testNegativeNumber() {
        // Negative numbers (converted to positive)
        assertEquals(6, RecursiveSumOfDigits.sumOfDigits(-123)); // |1 + 2 + 3| = 6
        assertEquals(10, RecursiveSumOfDigits.sumOfDigits(-28)); // |2 + 8| = 10
    }

    @Test
    public void testLargeNumber() {
        // Large numbers
        assertEquals(39, RecursiveSumOfDigits.sumOfDigits(987654)); // 9+8+7+6+5+4 = 39
    }
}
