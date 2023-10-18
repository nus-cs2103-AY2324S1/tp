package seedu.application.model.job;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.util.AppUtil;

/**
 * Represents a Job's company in the application book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompany(String)}
 */
public class Company {
    public static final String COMPANY_FIND_SPECIFIER = "-c";
    public static final String MESSAGE_CONSTRAINTS =
            "Company names should adhere to the following constraints:\n"
                    + "1. Start with an alphanumeric character\n"
                    + "2. Should not be blank";

    /*
     * The first character of the company must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}].*";

    public final String name;

    /**
     * Constructs a {@code Company}.
     *
     * @param name A valid company name.
     */
    public Company(String name) {
        requireNonNull(name);
        AppUtil.checkArgument(isValidCompany(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name, that is it matches the 2 constraints above.
     */
    public static boolean isValidCompany(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return name;
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
        return name.equalsIgnoreCase(otherCompany.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
