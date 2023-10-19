package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Object for sort list in.
 */
public class SortIn {
    public static final String MESSAGE_CONSTRAINTS =
            "Sort in should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String sortIn;

    /**
     * Constructs a {@code MrtStation}.
     *
     * @param sortIn A valid attribute to sort in.
     */
    public SortIn(String sortIn) {
        requireNonNull(sortIn);
        checkArgument(isValidSortIn(sortIn), MESSAGE_CONSTRAINTS);
        this.sortIn = sortIn;
    }

    /**
     * Returns true if a given string is a valid sort in attribute.
     */
    public static boolean isValidSortIn(String mrtStationName) {
        // return mrtStationName.matches(VALIDATION_REGEX);
        return true;
    }

    @Override
    public String toString() {
        return sortIn;
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
        return sortIn.equals(otherSortIn.sortIn);
    }

    @Override
    public int hashCode() {
        return sortIn.hashCode();
    }
}
