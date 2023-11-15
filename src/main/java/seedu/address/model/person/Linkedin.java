package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's linkedin in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLinkedin(String)}
 */
public class Linkedin {

    /**
     * Message constraints for Linkedin which is the format used for user input.
     */
    public static final String MESSAGE_CONSTRAINTS = "Linkedin should not be blank, no whitespaces and alphanumeric "
            + "characters only.\ne.g. john-doe-b9a38128a, which is the unique username on the linkedin "
            + "profile website.";

    /**
     * Regex for Linkedin when represented as the person's unique username on Linkedin.
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$)[a-zA-Z0-9_-]+$";


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
