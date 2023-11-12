package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's details in the address book.
 */
public class Details {

    public static final String MESSAGE_CONSTRAINTS = "Details should only contain alphanumeric characters "
            + "and spaces, and it should not be blank";

    /*
     * The first character of the details must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
    * Constructs a {@code Details}.
    *
    * @param details A valid details.
    */
    public Details(String details) {
        requireNonNull(details);
        checkArgument(isValidDetails(details), MESSAGE_CONSTRAINTS);
        value = details;
    }

    /**
    * Returns true if a given string is a valid details.
    */
    public static boolean isValidDetails(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof Details
            && value.equals(((Details) other).value));
    }
}
