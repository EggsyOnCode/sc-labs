package org.example;

import java.util.Stack;

public class RecursiveExpressionParser {

    public static double evaluateExpression(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        expression = expression.replaceAll("\\s+", "");
        if (!isValidExpression(expression)) {
            throw new IllegalArgumentException("Expression contains invalid characters or mismatched parentheses");
        }

        return evaluate(expression, 0, expression.length() - 1);
    }

    private static double evaluate(String expression, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Malformed expression");
        }

        // Handle parentheses
        if (expression.charAt(start) == '(' && expression.charAt(end) == ')') {
            return evaluate(expression, start + 1, end - 1);
        }

        // Evaluate addition and subtraction (lowest precedence)
        int pos = findOperatorPosition(expression, start, end, '+', '-');
        if (pos != -1) {
            double left = evaluate(expression, start, pos - 1);
            double right = evaluate(expression, pos + 1, end);
            return expression.charAt(pos) == '+' ? left + right : left - right;
        }

        // Evaluate multiplication and division (higher precedence)
        pos = findOperatorPosition(expression, start, end, '*', '/');
        if (pos != -1) {
            double left = evaluate(expression, start, pos - 1);
            double right = evaluate(expression, pos + 1, end);
            if (expression.charAt(pos) == '/') {
                if (right == 0) throw new ArithmeticException("Division by zero");
                return left / right;
            }
            return left * right;
        }

        // Parse numbers
        try {
            return Double.parseDouble(expression.substring(start, end + 1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format");
        }
    }


    private static int findOperatorPosition(String expression, int start, int end, char op1, char op2) {
        int parenthesisCount = 0;
        for (int i = end; i >= start; i--) {
            char ch = expression.charAt(i);
            if (ch == ')') parenthesisCount++;
            else if (ch == '(') parenthesisCount--;
            else if ((ch == op1 || ch == op2) && parenthesisCount == 0) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isValidExpression(String expression) {
        int parenthesisCount = 0;
        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.' || "+-*/()".indexOf(ch) != -1) {
                if (ch == '(') parenthesisCount++;
                if (ch == ')') parenthesisCount--;
                if (parenthesisCount < 0) return false;
            } else {
                return false;
            }
        }
        return parenthesisCount == 0;
    }
}
