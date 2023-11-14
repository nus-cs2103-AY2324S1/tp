package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's company in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompany(String)}
 */
public class Company {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?!\\s)[\\s\\S]{1,256}$";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Company(String name) {
        checkArgument(isValidCompany(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidCompany(String test) {
        if (test.isEmpty()) {
            return true;
        }
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

        Company otherName = (Company) other;
        return value.equals(otherName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
