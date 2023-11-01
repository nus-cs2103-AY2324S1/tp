package seedu.address.model.remark;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Remark in the employee book.
 */
public class Remark {

    public final String remark;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
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
}
