package seedu.address.model.contact;



/**
 * Immutably represents a {@link Contact}'s phone number.
 *
 * Constructor arguments must be valid as determined by
 * {@link #isValid(String)}.
 */
public class Phone {
    public final String value;

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValid(String value) {
        return value.matches("\\d{3,}");
    }

    /**
     * Constructs with the specified value.
     *
     * Constructor arguments must be valid as determined by
     * {@link #isValid(String)}.
     */
    public Phone(String value) {
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
        if (!(other instanceof Phone)) {
            return false;
        }
        Phone otherPhone = (Phone)other;

        return this.value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
