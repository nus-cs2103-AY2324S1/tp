package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format"
            + " local-part@domain.tld (tld = top-level domain)"
            + " and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters ("
            + SPECIAL_CHARACTERS + "). The local-part may not start or end with any special characters.\n"
            + "2. This is followed by a '@' , a domain name, a '.', and the top-level domain (tld). "
            + "The domain name is made up of domain labels separated by periods.\n"
            + "The domain name must:\n"
            + "    - end with a tld which is at least 2 characters long\n"
            + "    - have each domain label start and end with alphanumeric characters\n"
            + "    - have each domain label and tld consist of alphanumeric characters,"
            + " separated only by hyphens, if any.";
    // @author nubnubyas-reused
    // Reused from https://owasp.org/www-community/OWASP_Validation_Regex_Repository
    private static final String VALIDATION_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
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
        if (!(other instanceof Email)) {
            return false;
        }

        Email otherEmail = (Email) other;
        return value.equals(otherEmail.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
