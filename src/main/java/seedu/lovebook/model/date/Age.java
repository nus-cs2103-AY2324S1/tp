package seedu.lovebook.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date's age in the lovebook.
 * Guarantees: immutable; is valid as declared in {@link #isValidAge(String)}
 */
public class Age implements Comparable<Age> {
    public static final String MESSAGE_CONSTRAINTS =
            "Age should only contain positive numbers in the range 18-150 years.";
    public static final String VALIDATION_REGEX = "^(1[89]|[2-9][0-9]|1[0-4][0-9]|150)$"; // 18-150 Age Accepted
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

    @Override
    public int compareTo(Age o) {
        int thisAge = Integer.parseInt(this.toString());
        int otherAge = Integer.parseInt(o.toString());
        if (thisAge > otherAge) {
            return 1;
        } else if (thisAge < otherAge) {
            return -1;
        }
        return 0;
    }
}
