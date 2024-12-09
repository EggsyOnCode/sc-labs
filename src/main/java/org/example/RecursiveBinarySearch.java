package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecursiveBinarySearch {

    // Recursive Binary Search for Integer
    public static int binarySearchRecursive(int[] array, int target, int low, int high) {
        if (array == null || array.length == 0) return -1; // Handle null or empty array
        if (low > high) return -1; // Base case: not found

        int mid = low + (high - low) / 2;

        if (array[mid] == target) {
            return mid; // Target found
        } else if (target < array[mid]) {
            return binarySearchRecursive(array, target, low, mid - 1); // Search left half
        } else {
            return binarySearchRecursive(array, target, mid + 1, high); // Search right half
        }
    }

    // Recursive Binary Search for Strings
    public static int binarySearchRecursive(String[] array, String target, int low, int high) {
        if (array == null || array.length == 0) return -1; // Handle null or empty array
        if (low > high) return -1; // Base case: not found

        int mid = low + (high - low) / 2;

        if (array[mid].equals(target)) {
            return mid; // Target found
        } else if (target.compareTo(array[mid]) < 0) {
            return binarySearchRecursive(array, target, low, mid - 1); // Search left half
        } else {
            return binarySearchRecursive(array, target, mid + 1, high); // Search right half
        }
    }

    // Recursive Binary Search for Multiple Indices
    public static List<Integer> binarySearchMultiple(int[] array, int target, int left, int right) {
        List<Integer> indices = new ArrayList<>();
        if (array == null || array.length == 0) return indices;

        binarySearchHelper(array, target, left, right, indices);
        Collections.sort(indices); // Ensure indices are sorted
        return indices;
    }

    private static void binarySearchHelper(int[] array, int target, int left, int right, List<Integer> indices) {
        if (left > right) return;

        int mid = left + (right - left) / 2;

        if (array[mid] == target) {
            indices.add(mid);
            binarySearchHelper(array, target, left, mid - 1, indices); // Search left
            binarySearchHelper(array, target, mid + 1, right, indices); // Search right
        } else if (array[mid] < target) {
            binarySearchHelper(array, target, mid + 1, right, indices); // Search right
        } else {
            binarySearchHelper(array, target, left, mid - 1, indices); // Search left
        }
    }


    // Main method for testing
    public static void main(String[] args) {
        int[] sortedArray = {1, 2, 4, 4, 4, 5, 6, 7, 8};
        String[] sortedStringArray = {"apple", "banana", "cherry", "date", "fig", "grape"};

        // Single Target Search
        System.out.println("Index of 4: " + binarySearchRecursive(sortedArray, 4, 0, sortedArray.length - 1));
        System.out.println("Index of 'cherry': " + binarySearchRecursive(sortedStringArray, "cherry", 0, sortedStringArray.length - 1));

        // Multiple Target Search
        System.out.println("Indices of 4: " + binarySearchMultiple(sortedArray, 4, 0, sortedArray.length - 1));
    }
}
