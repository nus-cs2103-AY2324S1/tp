package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's numeric year level.
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)}
 */
public class Year {

    public static final String MESSAGE_CONSTRAINTS =
            "Year should only contain numbers, and it should be 1 digit long between 1 and 6.";

    public static final String VALIDATION_REGEX = "^[1-6]$";

    public final String value;

    /**
     * Constructs a {@code Year}.
     *
     * @param year A valid year.
     */
    public Year(String year) {
        requireNonNull(year);
        checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
        value = year;
    }

    /**
     * Returns true if a given string is a valid year.
     */
    public static boolean isValidYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Year)) {
            return false;
        }

        Year otherYear = (Year) other;
        return value.equals(otherYear.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
