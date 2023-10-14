package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Object for sort list by.
 */
public class SortBy {
    public static final String MESSAGE_CONSTRAINTS =
            "Sort by should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String sortBy;

    /**
     * Constructs a {@code MrtStation}.
     *
     * @param sortBy A valid attribute to sort by.
     */
    public SortBy(String sortBy) {
        requireNonNull(sortBy);
        checkArgument(isValidSortBy(sortBy), MESSAGE_CONSTRAINTS);
        this.sortBy = sortBy;
    }

    /**
     * Returns true if a given string is a valid sort by attribute.
     */
    public static boolean isValidSortBy(String mrtStationName) {
        // return mrtStationName.matches(VALIDATION_REGEX);
        return true;
    }

    @Override
    public String toString() {
        return sortBy;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortBy)) {
            return false;
        }

        SortBy otherSortBy = (SortBy) other;
        return sortBy.equals(otherSortBy.sortBy);
    }

    @Override
    public int hashCode() {
        return sortBy.hashCode();
    }
}
