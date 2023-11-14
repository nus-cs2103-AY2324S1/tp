package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's preferred contact method in the address book.
 */
public class PreferredContact {

    public static final String MESSAGE_CONSTRAINTS =
            "Preferred contact can take either phone or email, else it will be blank";

    public final String value;

    /**
     * Constructs a {@code Preferred Contact}.
     *
     * @param preferredContact of client
     */
    public PreferredContact(String preferredContact) {
        requireNonNull(preferredContact);
        checkArgument(isValidPreferredContact(preferredContact), MESSAGE_CONSTRAINTS);
        value = preferredContact;
    }

    /**
     * Returns true if a given string is a valid preferred contact.
     */
    public static boolean isValidPreferredContact(String test) {
        boolean result = test.equals("phone") || test.equals("email") || test.equals("");
        return result;
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
        if (!(other instanceof PreferredContact)) {
            return false;
        }

        PreferredContact otherPreferredContact = (PreferredContact) other;
        return value.equals(otherPreferredContact.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
