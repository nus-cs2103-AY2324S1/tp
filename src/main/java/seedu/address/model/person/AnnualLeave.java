package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's annual leave in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAnnualLeave(String)}
 */
public class AnnualLeave {
    public static final String MESSAGE_CONSTRAINTS =
            "Number of days of annual leave remaining should only contain numerical digits. "
                + "It should not contain dashes or spaces.";

    /*
     * The first character of the annual leave must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    /**
     * Constructs a {@code AnnualLeave}.
     *
     * @param annualLeave A valid annual leave.
     */
    public AnnualLeave(String annualLeave) {
        requireNonNull(annualLeave);
        checkArgument(isValidAnnualLeave(annualLeave), MESSAGE_CONSTRAINTS);
        value = annualLeave;
    }

    /**
     * Returns true if a given string is a valid number of days.
     */
    public static boolean isValidAnnualLeave(String test) {
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
        if (!(other instanceof AnnualLeave)) {
            return false;
        }

        AnnualLeave otherAnnualLeave = (AnnualLeave) other;
        return value.equals(otherAnnualLeave.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
