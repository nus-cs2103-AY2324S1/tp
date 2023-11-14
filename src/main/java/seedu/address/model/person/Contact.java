package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidContactName(String)}
 */
public class Contact {

    public static final String MESSAGE_CONSTRAINTS = "Contacts' names should a non-empty string of characters.";
    public static final String VALIDATION_REGEX = "[^\\s].*[a-zA-Z0-9].*";
    public static final String PARSE_CONTACT_DELIMITER = ", ";

    public final String contact;

    /**
     * Constructs a {@code Contact}.
     *
     * @param contact A valid contact.
     */
    public Contact(String contact) {
        requireNonNull(contact);
        checkArgument(isValidContactName(contact), MESSAGE_CONSTRAINTS);
        this.contact = contact;
    }

    /**
     * Returns true if a given string is a valid contact name.
     */
    public static boolean isValidContactName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return contact.equals(otherContact.contact);
    }

    @Override
    public int hashCode() {
        return contact.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + contact + ']';
    }

}
