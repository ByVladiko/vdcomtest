package model;

import java.text.ParseException;
import java.util.Objects;

public class Dimension {

    public static final String DELIM = " ";
    public static final String UNKNOWN = "?";

    private final String unit;
    private final Long value;

    private Dimension(String unit, Long value) {
        this.unit = unit;
        this.value = value;
    }

    public static Dimension of(String input) throws ParseException {
        if (input == null || input.isEmpty())
            throw new ParseException("Empty value of unit", -1);

        String[] parts = input.split(DELIM);
        if (parts.length != 2)
            throw new ParseException("Invalid number parts in dimension", -1);

        try {
            Long value = parts[0].equals(UNKNOWN) ? null : Long.parseLong(parts[0]);

            String unit = parts[1];
            if (unit == null || unit.isEmpty())
                throw new ParseException("Empty unit", parts[0].length() + 1);

            return new Dimension(unit, value);
        } catch (NumberFormatException ex) {
            throw new ParseException(ex.getMessage(), 0);
        }
    }

    public static Dimension of(String unit, long value) {
        if (unit == null || unit.isEmpty())
            throw new IllegalArgumentException("Empty value of unit");

        return new Dimension(unit, value);
    }

    public String getUnit() {
        return unit;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Dimension dimension = (Dimension) o;

        if (!Objects.equals(value, dimension.value))
            return false;

        return Objects.equals(unit, dimension.unit);
    }

    @Override
    public int hashCode() {
        int result = unit != null ? unit.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
