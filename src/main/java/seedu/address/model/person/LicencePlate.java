package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's car licence plate in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLicencePlate(String)}
 */
public class LicencePlate {

    public static final String MESSAGE_CONSTRAINTS
            = "Licence plates should be an alphanumeric string with 1-9 characters";

    /*
     * Should be an alphanumeric string with 1-9 characters
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]{1,9}$";

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
