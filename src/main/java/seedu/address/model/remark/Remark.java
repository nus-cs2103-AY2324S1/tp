package seedu.address.model.remark;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Remark in the employee book.
 */
public class Remark {
    public static final DateTimeFormatter VALID_DATE_FORMAT = ISO_LOCAL_DATE;

    public final String remark;
    public final LocalDate remarkDate;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        this.remark = remark;
        this.remarkDate = LocalDate.now();
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
        return remark.equals(otherRemark.remark) && remarkDate.equals(otherRemark.remarkDate);
    }

    @Override
    public int hashCode() {
        return remark.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return remark + "[written on: " + remarkDate.format(VALID_DATE_FORMAT) + "]";
    }
}
