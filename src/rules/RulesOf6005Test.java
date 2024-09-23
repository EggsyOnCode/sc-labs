/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package rules;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit tests for RulesOf6005.
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class RulesOf6005Test {

    /**
     * Tests the mayUseCodeInAssignment method.
     */
    @Test
    public void testMayUseCodeInAssignment() {
        assertFalse("Expected false: un-cited publicly-available code",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, false, false));
        assertTrue("Expected true: self-written required code",
                RulesOf6005.mayUseCodeInAssignment(true, false, true, true, true));
    }

    /**
     * Tests the scenario of cited external code that was not required for implementation.
     */
    @Test
    public void testMayUseCitedExternalCodeNotRequired() {
        assertFalse("Expected false: cited external code that was not required for implementation",
                RulesOf6005.mayUseCodeInAssignment(false, false, false, true, true));
    }

    /**
     * Tests self-written code not part of the coursework.
     */
    @Test
    public void testMayUseSelfWrittenNonCourseworkCode() {
        assertTrue("Expected true: self-written code, even if not part of the coursework",
                RulesOf6005.mayUseCodeInAssignment(true, false, false, true, false));
    }
}
