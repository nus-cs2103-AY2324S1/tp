package seedu.address.model.localcourse;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.messages.ConstraintMessage;

/**
 * Represents a Local Course's unit in SEPlendid.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocalUnit(Object)}
 */
public class LocalUnit {
    private final Double value;

    /**
     * Constructs an {@code LocalUnit}.
     *
     * @param localUnit A valid local unit.
     */
    public LocalUnit(String localUnit) {
        requireNonNull(localUnit);
        checkArgument(isValidLocalUnit(localUnit), ConstraintMessage.LOCALCOURSE_UNIT.toString());
        value = Double.parseDouble(localUnit);
    }

    /**
     * Constructs an {@code LocalUnit}.
     *
     * @param localUnit A valid local unit.
     */
    public LocalUnit(Double localUnit) {
        requireNonNull(localUnit);
        checkArgument(isValidLocalUnit(localUnit), ConstraintMessage.LOCALCOURSE_UNIT.toString());
        value = localUnit;
    }

    /**
     * Checks if the provided object is a valid local unit.
     * A valid local unit is a positive number.
     *
     * @param test The object to be validated.
     * @return true if the object is a valid local unit, false otherwise.
     */
    public static boolean isValidLocalUnit(Object test) {
        if (test == null) {
            // Handle null input gracefully
            return false;
        }

        if (test instanceof String) {
            // If the input is a String, attempt to parse it as a double
            try {
                double parsedValue = Double.parseDouble((String) test);

                // Check if the parsed value is greater than 0 and lte 10000
                return parsedValue >= 0 && parsedValue <= 10000;
            } catch (NumberFormatException e) {
                // If parsing fails, return false
                return false;
            }
        }

        if (test instanceof Number) {
            // If the input is a Number, check if its double value is greater than 0
            return ((Number) test).doubleValue() >= 0;
        }

        // If the input is neither a String nor a Number, return false
        return false;
    }

    public Double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LocalUnit)) {
            return false;
        }

        LocalUnit otherLocalName = (LocalUnit) other;
        // performs semantic check
        return value.equals(otherLocalName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
