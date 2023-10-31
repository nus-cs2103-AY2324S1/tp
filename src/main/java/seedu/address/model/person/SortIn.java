package seedu.address.model.person;

import java.time.YearMonth;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Object for sort list in.
 */
public class SortIn {
    public static final String MESSAGE_CONSTRAINTS =
            "Sort in should only be ASC or DESC.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String SORT_IN;
    public enum ValidSequence { ASC, DESC; }

    /**
     * Constructs a {@code MrtStation}.
     *
     * @param sortIn A valid attribute to sort in.
     */
    public SortIn(String sortIn) {
        requireNonNull(sortIn);
        checkArgument(isValidSortIn(sortIn), MESSAGE_CONSTRAINTS);
        this.SORT_IN = sortIn.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid sort in attribute.
     */
    public static boolean isValidSortIn(String sequence) {
        try {
            ValidSequence enumValue = ValidSequence.valueOf(sequence.toUpperCase());
            return enumValue == ValidSequence.ASC || enumValue == ValidSequence.DESC;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return SORT_IN;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortIn)) {
            return false;
        }

        SortIn otherSortIn = (SortIn) other;
        return SORT_IN.equals(otherSortIn.SORT_IN);
    }

    @Override
    public int hashCode() {
        return SORT_IN.hashCode();
    }
}
