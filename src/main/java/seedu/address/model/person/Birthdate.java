package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Birthdate {
    public static final String MESSAGE_CONSTRAINTS = "Birthdates should be of the form MM/DD/YYYY";

    /*
     * The first character of the birthdate must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d{2}\\/\\d{2}\\/\\d{4}";

    public final String value;

    public Birthdate(String birthdate) {
        requireNonNull(birthdate);
        value = birthdate;
    }

    /**
     * Returns if a given string is a valid birthdate.
     */
    public static boolean isValidBirthdate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthdate // instanceof handles nulls
                && value.equals(((Birthdate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}