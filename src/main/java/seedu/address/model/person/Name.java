package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, should not be blank and "
                    + "should be at max 50 characters in length";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns if a given string is a valid name.
     *
     * @param test The string to test for validity.
     * @return True if the string matches the expected name format, false otherwise.
     */
    public static boolean isValidName(String test) {
        if (test.length() > 50) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a string representation of this name.
     *
     * @return A string representation of this name.
     */
    @Override
    public String toString() {
        return fullName;
    }

    /**
     * Indicates whether some other object is "equal to" this name.
     *
     * @param other The reference object with which to compare.
     * @return True if the other object is equal to this name, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equals(otherName.fullName);
    }

    /**
     * Returns a hash code value for this name.
     *
     * @return A hash code value for this name.
     */
    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    /**
     * Truncates the full name to a maximum of 15 characters, appending an ellipsis (...) for portions
     * exceeding this limit.
     *
     * @return The truncated full name adhering to the 15-character limit.
     */
    public String truncatedName() {
        if (fullName.length() > 15) {
            return fullName.substring(0, 12) + "...";
        }
        return fullName;
    }

}
