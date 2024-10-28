package org.example;

        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;

public class PermutationGenerator {

    // Recursive function to generate permutations
    public static List<String> generatePermutations(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        List<String> result = new ArrayList<>();
        if (input.isEmpty()) {
            return result;
        }
        generatePermutationsHelper(input.toCharArray(), 0, result);
        return result;
    }

    private static void generatePermutationsHelper(char[] chars, int index, List<String> result) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
        } else {
            for (int i = index; i < chars.length; i++) {
                swap(chars, index, i);
                generatePermutationsHelper(chars, index + 1, result);
                swap(chars, index, i); // backtrack
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // Generate permutations with option to include unique permutations only
    public static List<String> generatePermutations(String input, boolean unique) {
        List<String> allPermutations = generatePermutations(input);
        if (unique) {
            Set<String> uniquePermutations = new HashSet<>(allPermutations);
            return new ArrayList<>(uniquePermutations);
        }
        return allPermutations;
    }

    // Iterative function to generate permutations
    public static List<String> generatePermutationsIterative(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        List<String> permutations = new ArrayList<>();
        if (input.isEmpty()) {
            return permutations;
        }
        permutations.add(String.valueOf(input.charAt(0)));
        for (int i = 1; i < input.length(); i++) {
            char current = input.charAt(i);
            List<String> newPermutations = new ArrayList<>();
            for (String perm : permutations) {
                for (int j = 0; j <= perm.length(); j++) {
                    newPermutations.add(perm.substring(0, j) + current + perm.substring(j));
                }
            }
            permutations = newPermutations;
        }
        return permutations;
    }
}
