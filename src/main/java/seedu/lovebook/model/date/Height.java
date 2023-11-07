package seedu.lovebook.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date's lovebook in the lovebook.
 * Guarantees: immutable; is valid as declared in {@link #isValidHeight(String)}
 */
public class Height implements Comparable<Height> {

    public static final String MESSAGE_CONSTRAINTS = "Height can take any values between 1 and 250cm";

    /*
     * The first character of the Height must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(1\\d{2}|2[0-4]\\d|250)$";

    public final String value;

    /**
     * Constructs an {@code Height}.
     *
     * @param height A valid lovebook.
     */
    public Height(String height) {
        requireNonNull(height);
        checkArgument(isValidHeight(height), MESSAGE_CONSTRAINTS);
        value = height;
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidHeight(String test) {
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
        if (!(other instanceof Height)) {
            return false;
        }

        Height otherHeight = (Height) other;
        return value.equals(otherHeight.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Height o) {
        int thisHeight = Integer.parseInt(this.toString());
        int otherHeight = Integer.parseInt(o.toString());
        if (thisHeight > otherHeight) {
            return 1;
        } else if (thisHeight < otherHeight) {
            return -1;
        }
        return 0;
    }
}
