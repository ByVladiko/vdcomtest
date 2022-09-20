package graph;

import exception.ConverterException;
import model.Dimension;
import model.Expression;

import java.util.*;
import java.util.function.BiFunction;

public class Converter {

    private final Map<String, Map<String, Long>> multiMap = new HashMap<>();
    private final List<Expression> expressionsToSolved = new ArrayList<>();

    public List<Expression> resolveExpressions(List<Expression> expressions) {
        fillInputExpression(expressions);

        if (expressionsToSolved.isEmpty())
            return Collections.emptyList();

        if (multiMap.isEmpty())
            throw new ConverterException("No data to solve");

        for (Expression expression : expressionsToSolved) {
            resolveExpression(expression);
        }

        return expressionsToSolved;
    }

    private void resolveExpression(Expression expression) {
        Dimension left = expression.getLeft();
        String targetUnit = expression.getRight().getUnit();

        Stack<String> stack = new Stack<>();
        List<Pair<Long, BiFunction<Long, Long, Long>>> operationsList = new ArrayList<>();

        Map<String, Long> valuesMap = multiMap.get(left.getUnit());
        if (valuesMap == null) {
            Pair<String, Long> divider = getDivider(left.getUnit());
            if (divider == null) {
                return;
            } else {

            }
        } else {
            for (Map.Entry<String, Long> stringLongEntry : valuesMap.entrySet()) {

            }
        }
    }

    private Pair<String, Long> getDivider(String unit) {
        for (Map<String, Long> value : multiMap.values()) {
            Long divider = value.get(unit);
            if (divider != null)
                return new Pair<>(unit, divider);
        }
        return null;
    }

    private void fillInputExpression(List<Expression> expressions) {
        for (Expression expression : expressions) {
            if (expression.isUnresolved()) {
                expressionsToSolved.add(expression);
            } else {
                saveExpression(expression);
            }
        }
    }

    private void saveExpression(Expression expression) {
        Dimension left = expression.getLeft();
        Dimension right = expression.getRight();

        Dimension key;
        Dimension value;
        if (left.getValue() <= right.getValue()) {
            key = left;
            value = right;
        } else {
            key = right;
            value = left;
        }

        putValueToMultiMap(key, value);
    }

    private void putValueToMultiMap(Dimension key, Dimension value) {
        Map<String, Long> unitValue = multiMap.get(key.getUnit());
        if (unitValue == null) {
            unitValue = new HashMap<>();
            unitValue.put(value.getUnit(), value.getValue() / key.getValue());
        } else {
            unitValue.computeIfAbsent(value.getUnit(), k -> value.getValue() / key.getValue());
        }
        multiMap.put(key.getUnit(), unitValue);
    }

    class Pair<F, S> {
        private F first;
        private S second;

        private Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o == null || getClass() != o.getClass())
                return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;

            if (!Objects.equals(first, pair.first))
                return false;

            return Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }
    }
}
