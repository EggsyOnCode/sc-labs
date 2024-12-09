package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveBinarySearchTest {
    // Test for Integer Binary Search
    @Test
    public void testBinarySearchRecursiveIntegers() {
        int[] sortedArray = {1, 2, 4, 5, 6, 7, 8};

        // Positive cases
        assertEquals(2, RecursiveBinarySearch.binarySearchRecursive(sortedArray, 4, 0, sortedArray.length - 1));
        assertEquals(4, RecursiveBinarySearch.binarySearchRecursive(sortedArray, 6, 0, sortedArray.length - 1));

        // Negative cases
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(sortedArray, 10, 0, sortedArray.length - 1));
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(sortedArray, 0, 0, sortedArray.length - 1));

        // Edge cases
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(new int[]{}, 4, 0, -1)); // Empty array
        assertEquals(0, RecursiveBinarySearch.binarySearchRecursive(new int[]{3}, 3, 0, 0));  // Single-element array
    }

    // Test for String Binary Search
    @Test
    public void testBinarySearchRecursiveStrings() {
        String[] sortedArray = {"apple", "banana", "cherry", "date", "fig", "grape"};

        // Positive cases
        assertEquals(2, RecursiveBinarySearch.binarySearchRecursive(sortedArray, "cherry", 0, sortedArray.length - 1));
        assertEquals(0, RecursiveBinarySearch.binarySearchRecursive(sortedArray, "apple", 0, sortedArray.length - 1));

        // Negative cases
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(sortedArray, "mango", 0, sortedArray.length - 1));
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(sortedArray, "kiwi", 0, sortedArray.length - 1));

        // Edge cases
        assertEquals(-1, RecursiveBinarySearch.binarySearchRecursive(new String[]{}, "apple", 0, -1)); // Empty array
        assertEquals(0, RecursiveBinarySearch.binarySearchRecursive(new String[]{"pear"}, "pear", 0, 0)); // Single-element array
    }

    // Test for Multiple Indices Binary Search
    @Test
    public void testBinarySearchMultipleIndices() {
        int[] sortedArray = {1, 2, 4, 4, 4, 5, 6, 7, 8};

        // Positive case: multiple indices
        List<Integer> expectedIndices = Arrays.asList(2, 3, 4);
        assertEquals(expectedIndices, RecursiveBinarySearch.binarySearchMultiple(sortedArray, 4, 0, sortedArray.length - 1));

        // Positive case: single index
        expectedIndices = Arrays.asList(6);
        assertEquals(expectedIndices, RecursiveBinarySearch.binarySearchMultiple(sortedArray, 6, 0, sortedArray.length - 1));

        // Negative case: target not in the array
        expectedIndices = Arrays.asList();
        assertEquals(expectedIndices, RecursiveBinarySearch.binarySearchMultiple(sortedArray, 10, 0, sortedArray.length - 1));

        // Edge case: empty array
        expectedIndices = Arrays.asList();
        assertEquals(expectedIndices, RecursiveBinarySearch.binarySearchMultiple(new int[]{}, 4, 0, -1));

        // Edge case: single-element array
        expectedIndices = Arrays.asList(0);
        assertEquals(expectedIndices, RecursiveBinarySearch.binarySearchMultiple(new int[]{3}, 3, 0, 0));
    }

}