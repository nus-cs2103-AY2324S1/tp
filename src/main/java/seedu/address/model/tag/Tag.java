package seedu.address.model.tag;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Name;



/**
 * Immutably represents a {@link Contact}'s tag.
 *
 * Constructor arguments must be valid as determined by
 * {@link #isValid(String)}.
 */
public final class Tag {
    public final String value;

    /**
     * Returns whether the specified value is valid.
     *
     * Tags must be an alphanumeric and may contain spaces, but cannot start with a space.
     */
    public static boolean isValid(String value) {
        return value.matches(Name.REGEX_VALID);
    }

    /**
     * Constructs with the specified value.
     *
     * Constructor arguments must be valid as determined by
     * {@link #isValid(String)}.
     */
    public Tag(String value) {
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
        if (!(other instanceof Tag)) {
            return false;
        }
        Tag otherTag = (Tag)other;

        return this.value.equals(otherTag.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
