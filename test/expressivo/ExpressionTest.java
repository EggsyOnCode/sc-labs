/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   TODO

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }


    @Test
    public void testToString() {
        Expression expr1 = new Addition(new Constant(1), new Variable("x"));
        assertEquals("1.0 + x", expr1.toString());

        Expression expr2 = new Multiplication(new Variable("x"), new Constant(2));
        assertEquals("x * 2.0", expr2.toString());
    }

    @Test
    public void testEquals() {
        Expression expr1 = new Addition(new Constant(1), new Variable("x"));
        Expression expr2 = new Addition(new Constant(1), new Variable("x"));
        Expression expr3 = new Addition(new Variable("x"), new Constant(1));

        assertTrue(expr1.equals(expr2));
    }

    @Test
    public void testHashCode() {
        Expression expr1 = new Addition(new Constant(1), new Variable("x"));
        Expression expr2 = new Addition(new Constant(1), new Variable("x"));
        assertEquals(expr1.hashCode(), expr2.hashCode());
    }

}
