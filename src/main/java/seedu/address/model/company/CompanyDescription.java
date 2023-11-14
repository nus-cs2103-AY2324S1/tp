package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompanyDescription(String)}
 */
public class CompanyDescription {

    public static final String MESSAGE_CONSTRAINTS =
        "Descriptions can be any non-empty string and should not be blank.";
    public static final String VALIDATION_REGEX = "\\S+.*";
    public final String value;

    /**
     * Constructs a {@code CompanyDescription}.
     *
     * @param companyDescription A valid company description.
     */
    public CompanyDescription(String companyDescription) {
        requireNonNull(companyDescription);
        checkArgument(isValidCompanyDescription(companyDescription), MESSAGE_CONSTRAINTS);
        value = companyDescription;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidCompanyDescription(String test) {
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
        if (!(other instanceof CompanyDescription)) {
            return false;
        }

        CompanyDescription otherCompanyDescription = (CompanyDescription) other;
        return value.equals(otherCompanyDescription.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
