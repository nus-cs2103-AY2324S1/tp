package seedu.address.model.contact;



/**
 * Immutably represents a {@link Contact}'s name.
 *
 * Constructor arguments must be valid as determined by
 * {@link #isValid(String)}.
 */
public class Name {
    /*
     * Disallows a space as the first character. This prevents " " from being
     * valid.
     */
    public static final String REGEX_VALID = "[a-zA-Z\\d][a-zA-Z\\d ]*";

    public final String value;

    /**
     * Returns whether the specified value is valid.
     *
     * Names must be an alphanumeric and may contain spaces, but cannot start with a space.
     */
    public static boolean isValid(String value) {
        return value.matches(REGEX_VALID);
    }

    /**
     * Constructs with the specified value.
     *
     * Constructor arguments must be valid as determined by
     * {@link #isValid(String)}.
     */
    public Name(String value) {
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
        if (!(other instanceof Name)) {
            return false;
        }
        Name otherName = (Name)other;

        return this.value.equals(otherName.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
