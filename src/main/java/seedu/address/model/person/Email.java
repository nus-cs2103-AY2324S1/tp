package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format [LOCAL-PART]@[SERVER].[DOMAIN], "
            + "e.g. example@mail.com";

    public static final String VALIDATION_REGEX = "^(?![\\._-])(?!.*\\.\\.)[\\w-_+\\.]*(?!_)\\w@"
            + "((?![\\.-])(?!.*[\\._-]$)[A-Za-z0-9-]*(?!_)\\w\\.)+[A-Za-z0-9]{2,4}$";

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

    /**
     * Returns a truncated Email if it is more than 30 characters long.
     */
    public String shortEmail() {
        if (this.value.length() > 30) {
            return this.value.substring(0, 30) + "...";
        }
        return this.value;
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
        return value.toLowerCase().equals(otherEmail.value.toLowerCase());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
