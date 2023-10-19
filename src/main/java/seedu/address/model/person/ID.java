package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's ID.
 * Guarantees: immutable; is valid as declared in {@link #isValidID(String)}
 */
public class ID {

    public static final String MESSAGE_CONSTRAINTS = "Please enter the full ID! IDs should only contain alphanumeric "
            + "characters, with no special characters or space, and it should not be blank";

    /*
     * The ID format is A followed by 7 digits and ending with a letter.
     */
    public static final String VALIDATION_REGEX = "[A\\d{7}[A-Za-z]][A\\d{7}[A-Za-z] ]*";

    public final String value;

    /**
     * Constructs an {@code ID}.
     *
     * @param id A valid ID.
     */
    public ID(String id) {
        requireNonNull(id);
        checkArgument(isValidID(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given string is a valid ID.
     */
    public static boolean isValidID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ID // instanceof handles nulls
                && value.equals(((ID) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
