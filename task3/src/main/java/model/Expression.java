package model;

import java.text.ParseException;
import java.util.Objects;

public class Expression {

    public static final String EQUAL_SYMBOL = "=";

    private final Dimension left;
    private Dimension right;
    private boolean unresolved;

    private Expression(Dimension left, Dimension right) {
        this.left = left;
        this.right = right;
        this.unresolved = right.getValue() == null;
    }

    public static Expression of(String input) throws ParseException {
        if (input == null || input.isEmpty())
            throw new ParseException("Empty value", -1);

        String[] parts = input.split(EQUAL_SYMBOL);
        if (parts.length != 2)
            throw new ParseException("Invalid number parts in expression", -1);

        Dimension left = Dimension.of(parts[0].trim());
        Dimension right = Dimension.of(parts[1].trim());

        if (left.getValue() == null)
            throw new ParseException("Only right side of expression can be unknown", 0);

        return new Expression(left, right);
    }

    public static Expression of(Dimension left, Dimension right) {
        if (left == null || right == null)
            throw new IllegalArgumentException("Empty value argument");

        return new Expression(left, right);
    }

    public void putAnswer(long answer) {
        if (!unresolved)
            return;

        right = Dimension.of(right.getUnit(), answer);
        unresolved = true;
    }

    public Dimension getLeft() {
        return left;
    }

    public Dimension getRight() {
        return right;
    }

    public boolean isUnresolved() {
        return unresolved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Expression that = (Expression) o;

        if (!Objects.equals(left, that.left))
            return false;

        return Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
