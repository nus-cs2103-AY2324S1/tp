package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's age in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAge(String)}
 */
public class Age {

    public static final String MESSAGE_CONSTRAINTS =
            "Age should only contain integers between 0 and 149 both inclusive.";
    public static final String VALIDATION_REGEX = "\\b[0-9]|[1-9][0-9]|1[0-4][0-9]\\b";
    public final String value;

    /**
     * Constructs a {@code Age}.
     *
     * @param age A valid age.
     */
    public Age(String age) {
        requireNonNull(age);
        checkArgument(isValidAge(age), MESSAGE_CONSTRAINTS);
        value = age;
    }

    /**
     * Returns true if a given string is a valid age.
     */
    public static boolean isValidAge(String test) {
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
        if (!(other instanceof Age)) {
            return false;
        }

        Age otherAge = (Age) other;
        return value.equals(otherAge.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public Age getCopy() {
        return new Age(this.value);
    }
}
