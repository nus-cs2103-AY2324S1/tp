package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's linkedin in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLinkedin(String)}
 */
public class Linkedin {
    public static final String MESSAGE_CONSTRAINTS = "Linkedin can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Linkedin}.
     *
     * @param linkedin A valid linkedin.
     */
    public Linkedin(String linkedin) {
        requireNonNull(linkedin);
        checkArgument(isValidLinkedin(linkedin), MESSAGE_CONSTRAINTS);
        value = linkedin;
    }

    /**
     * Returns true if a given string is a valid linkedin handle.
     */
    public static boolean isValidLinkedin(String test) {
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
        if (!(other instanceof Linkedin)) {
            return false;
        }

        Linkedin otherLinkedin = (Linkedin) other;
        return value.equals(otherLinkedin.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
