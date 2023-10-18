package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's car licence plate in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLicencePlate(String)}
 */
public class LicencePlate {

    public static final String MESSAGE_CONSTRAINTS =
            "Licence plates should be in the format Sxx####y which follows the following constraints:\n"
            + "1. S - starts with the letter S, which stands for private vehicle\n"
            + "2. x - Alphabetical series (all except 'I' and 'O')\n"
            + "3. #### - Numerical series (from 1 to 9999, without leading zeroes)\n"
            + "4. y - Checksum letter (all except 'F', 'I', 'N', 'O', 'Q', 'V' and 'W'";

    /*
     * Should be an alphanumeric string satisfying the above constraints
     */
    public static final String VALIDATION_REGEX = "^S[^IO]{2}\\d{1,4}[A-EGHJKLMPRSTUXYZ]$";

    public final String value;

    /**
     * Constructs a {@code LicencePlate}.
     *
     * @param licencePlate A valid licence plate.
     */
    public LicencePlate(String licencePlate) {
        requireNonNull(licencePlate);
        checkArgument(isValidLicencePlate(licencePlate), MESSAGE_CONSTRAINTS);
        value = licencePlate;
    }

    /**
     * Returns true if a given string is a valid licence plate.
     */
    public static boolean isValidLicencePlate(String test) {
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
        if (!(other instanceof LicencePlate)) {
            return false;
        }

        LicencePlate otherLicencePlate = (LicencePlate) other;
        return value.equals(otherLicencePlate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
