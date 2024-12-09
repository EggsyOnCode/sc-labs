package org.example;

public class RecursiveSumOfDigits {

    /**
     * Calculates the sum of digits of a given non-negative integer.
     *
     * @param number the input number
     * @return the sum of its digits
     */
    public static int sumOfDigits(int number) {
        // Handle negative numbers
        if (number < 0) {
            number = Math.abs(number);
        }

        // Base case: if the number is 0, return 0
        if (number == 0) {
            return 0;
        }

        // Recursive step: extract the last digit and add it to the sum of the rest
        return (number % 10) + sumOfDigits(number / 10);
    }

}
