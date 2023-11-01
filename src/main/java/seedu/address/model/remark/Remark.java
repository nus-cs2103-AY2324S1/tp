package seedu.address.model.remark;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Remark in the employee book.
 */
public class Remark {
    public static final String MESSAGE_CONSTRAINTS = "Remarks must not be empty";

    public final String remark;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        this.remark = remark;
    }

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
        return remark.equals(otherRemark.remark) || remark.equalsIgnoreCase(otherRemark.remark);
    }

    @Override
    public int hashCode() {
        return remark.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return remark;
    }

    /**
     * Returns true if remark is non-empty.
     *
     * @param test Remark to be tested.
     * @return True if the remark is non-empty.
     */
    public static boolean isValidRemark(String test) {
        if (!test.isBlank()) {
            return true;
        } else {
            return false;
        }
    }
}
