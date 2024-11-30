package expressivo;

import java.util.Objects;

public class Addition implements Expression {
    private final Expression left;
    private final Expression right;

    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        // No parentheses added in the string format to match normalized structure
        return left.toString() + " + " + right.toString();
    }

    @Override
    public boolean equals(Object thatObject) {
        if (this == thatObject) return true;
        if (thatObject == null || getClass() != thatObject.getClass()) return false;

        Addition addition = (Addition) thatObject;

        // Normalize both sides before comparing
        Expression normalizedLeft = normalize(this.left);
        Expression normalizedRight = normalize(this.right);

        Expression otherNormalizedLeft = normalize(addition.left);
        Expression otherNormalizedRight = normalize(addition.right);

        // Compare normalized expressions for equality
        return (normalizedLeft.equals(otherNormalizedLeft) && normalizedRight.equals(otherNormalizedRight)) ||
                (normalizedLeft.equals(otherNormalizedRight) && normalizedRight.equals(otherNormalizedLeft)); // Commutative check
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    // Normalize the expression by simplifying it
    private Expression normalize(Expression expr) {
        // Here we just return the expression itself for now,
        // but we could implement a more complex normalization if necessary (e.g., flattening nested additions)
        return expr;
    }
}