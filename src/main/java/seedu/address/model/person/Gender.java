package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's gender.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
        "Gender should be 'M' or 'F' (case-insensitive).";

    public static final String VALIDATION_REGEX = "(?i)[MF]"; // Case-insensitive regex for M or F

    public final String value;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender ('M' or 'F').
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        value = gender.toUpperCase(); // Store as uppercase to ensure consistency
    }

    /**
     * Returns true if a given string is a valid gender ('M' or 'F').
     */
    public static boolean isValidGender(String test) {
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
        if (!(other instanceof Gender)) {
            return false;
        }

        Gender otherGender = (Gender) other;
        return value.equals(otherGender.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
