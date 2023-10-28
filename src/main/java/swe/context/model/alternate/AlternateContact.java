package swe.context.model.alternate;

import swe.context.model.contact.Contact;


/**
 * Immutably represents a {@link Contact}'s tag.
 *
 * Constructor arguments must be valid as determined by
 * {@link #isValid(String)}.
 */
public final class AlternateContact {

    public final String value;

    /**
     * Returns whether the specified value is valid.
     *
     * Alternate Contacts must roughly be of the form social-media@name.
     */
    public static boolean isValid(String value) {
        return value.matches(
                "^[a-zA-Z\\d]+(?:[a-zA-Z\\d]+)*"
                        + "@(?:[a-zA-Z\\d]+(?:[+_.-][a-zA-Z\\d]+)*)+"
        );
    }

    /**
     * Constructs with the specified value.
     *
     * Constructor arguments must be valid as determined by
     * {@link #isValid(String)}.
     */
    public AlternateContact(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof also handles nulls
        if (!(other instanceof AlternateContact)) {
            return false;
        }
        AlternateContact otherAlternateContact = (AlternateContact) other;

        return this.value.equals(otherAlternateContact.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
