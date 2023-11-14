package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's nationality in StudentConnect.
 * Guarantees: immutable; is valid as declared in {@link #isValidNationality(String)}
 */
public class Nationality {

    public static final String MESSAGE_CONSTRAINTS =
        "Nationality should be either 'local' or 'foreigner'";

    public static final String VALIDATION_REGEX = "(?i)local|foreigner";

    public final String value;

    /**
     * Constructs a {@code Nationality}.
     *
     * @param nationality A valid nationality.
     */
    public Nationality(String nationality) {
        requireNonNull(nationality);
        checkArgument(isValidNationality(nationality), MESSAGE_CONSTRAINTS);
        value = nationality.toLowerCase(); // Store as lowercase to ensure consistency
    }

    /**
     * Returns true if a given string is a valid nationality.
     */
    public static boolean isValidNationality(String test) {
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
        if (!(other instanceof Nationality)) {
            return false;
        }

        Nationality otherNationality = (Nationality) other;
        return value.equals(otherNationality.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
