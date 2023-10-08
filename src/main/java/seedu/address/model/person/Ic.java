package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's nric in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIc(String)}
 */
public class Ic {


    public static final String MESSAGE_CONSTRAINTS =
            "Ic should start with S or T, followed by 7 numbers, and ends with a letter. " +
                    "Letters must be in all caps. Empty strings are not allowed\n";
    public static final String VALIDATION_REGEX = "^[TS]\\d{7}[A-Z]$";
    public final String value;

    /**
     * Constructs a {@code Ic}.
     *
     * @param ic A valid ic.
     */
    public Ic(String ic) {
        requireNonNull(ic);
        checkArgument(isValidIc(ic), MESSAGE_CONSTRAINTS);
        value = ic;
    }

    /**
     * Returns true if a given string is a valid nric.
     */
    public static boolean isValidIc(String test) {
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
        if (!(other instanceof Ic)) {
            return false;
        }

        Ic otherIc = (Ic) other;
        return value.equals(otherIc.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
