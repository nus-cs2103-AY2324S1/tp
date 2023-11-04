package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Remark field in a given booking.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS =
            "Remarks should not be more than 50 characters.";

    /*
     * This regex matches an empty string (i.e., an optional remark) or
     * a string of 0 to 500 characters/spaces.
     */
    public static final String VALIDATION_REGEX = "^$|.{1,50}";

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        if (!isValidRemark(remark)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        value = remark;
    }

    /**
     * Returns if a given string is a valid remark.
     *
     * @param test The string to test for validity.
     * @return True if the string matches the expected remark format, false otherwise.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a string representation of this remark.
     *
     * @return A string representation of this remark.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Indicates whether some other object is "equal to" this remark.
     *
     * @param other The reference object with which to compare.
     * @return True if the other object is equal to this remark, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return value.equals(otherRemark.value);
    }

    /**
     * Returns a hash code value for this remark.
     *
     * @return A hash code value for this remark.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
