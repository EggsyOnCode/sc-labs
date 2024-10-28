package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PermutationGeneratorTest {

    @Test
    public void testGeneratePermutationsWithValidInput() {
        List<String> permutations = PermutationGenerator.generatePermutations("abc");
        assertEquals(6, permutations.size(), "There should be 6 permutations for 'abc'");
    }

    @Test
    public void testGeneratePermutationsWithDuplicates() {
        List<String> permutations = PermutationGenerator.generatePermutations("aab");
        assertEquals(6, permutations.size(), "There should be 6 permutations for 'aab', including duplicates");
    }

    @Test
    public void testGeneratePermutationsUnique() {
        List<String> uniquePermutations = PermutationGenerator.generatePermutations("aab", true);
        assertEquals(3, uniquePermutations.size(), "There should be 3 unique permutations for 'aab'");
    }

    @Test
    public void testGeneratePermutationsEmptyInput() {
        List<String> permutations = PermutationGenerator.generatePermutations("");
        assertTrue(permutations.isEmpty(), "Permutations of an empty string should be empty");
    }

    @Test
    public void testGeneratePermutationsNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PermutationGenerator.generatePermutations(null);
        });
        assertEquals("Input string cannot be null", exception.getMessage());
    }

    @Test
    public void testGeneratePermutationsIterative() {
        List<String> iterativePermutations = PermutationGenerator.generatePermutationsIterative("abc");
        List<String> recursivePermutations = PermutationGenerator.generatePermutations("abc");
        assertEquals(iterativePermutations.size(), recursivePermutations.size(),
                "Iterative and recursive methods should return the same number of permutations");
        assertTrue(iterativePermutations.containsAll(recursivePermutations),
                "Iterative results should contain all recursive results");
    }
}
