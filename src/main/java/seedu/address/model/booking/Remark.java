package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Remark {

    public static final String MESSAGE_CONSTRAINTS =
            "Remarks should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the remark must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullRemark;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        fullRemark = remark;
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
        return fullRemark;
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
        return fullRemark.equals(otherRemark.fullRemark);
    }

    /**
     * Returns a hash code value for this remark.
     *
     * @return A hash code value for this remark.
     */
    @Override
    public int hashCode() {
        return fullRemark.hashCode();
    }
}
