package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a car insurance company in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompany(String)}
 */
public class Company {

    public static final String MESSAGE_CONSTRAINTS =
            "Company name should not be empty, and can contain alphanumeric and/or special characters and whitespaces.";

    /*
     * Company name should not be empty, and can contain alphanumeric and/or special characters and whitespaces.
     */
    public static final String VALIDATION_REGEX = "\\S.*";
    public static final String DEFAULT_VALUE = "!@#NO_COMPANY!@#";

    public final String value;

    /**
     * Constructs a {@code Company}.
     *
     * @param company A valid company.
     */
    public Company(String company) {
        requireNonNull(company);
        checkArgument(isValidCompany(company), MESSAGE_CONSTRAINTS);
        value = company;
    }

    /**
     * Returns true if a given string is a valid company.
     */
    public static boolean isValidCompany(String test) {
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
        if (!(other instanceof Company)) {
            return false;
        }

        Company otherCompany = (Company) other;
        return value.equals(otherCompany.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
