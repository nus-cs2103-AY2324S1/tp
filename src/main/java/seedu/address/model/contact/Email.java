package seedu.address.model.contact;



/**
 * Immutably represents a {@link Contact}'s email.
 *
 * Constructor arguments must be valid as determined by
 * {@link #isValid(String)}.
 */
public class Email {
    public final String value;

    /**
     * Returns whether the specified value is valid.
     *
     * Emails must roughly be of the form example_email@foo-domain.sg.
     */
    public static boolean isValid(String value) {
        return value.matches(
            "^[a-zA-Z\\d]+(?:[+_.-][a-zA-Z\\d]+)*"
                    + "@(?:[a-zA-Z\\d]+(?:-[a-zA-Z\\d]+)*\\.)+"
                    + "(?:[a-zA-Z\\d]+(?:-[a-zA-Z\\d]+)*){2,}$"
        );
    }

    /**
     * Constructs with the specified value.
     *
     * Constructor arguments must be valid as determined by
     * {@link #isValid(String)}.
     */
    public Email(String value) {
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
        if (!(other instanceof Email)) {
            return false;
        }
        Email otherEmail = (Email)other;

        return this.value.equals(otherEmail.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
