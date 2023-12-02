package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {
    public static final int MAX_NAME_LENGTH = 100;
    public static final String MESSAGE_CONSTRAINTS_NON_EMPTY =
            "Oops! Company's name should not be blank. Please try again with a valid company name.";

    public static final String MESSAGE_CONSTRAINTS_INVALID_REGEX =
            "Oops! Company's name should only contain alphanumeric characters and spaces! Please try again with"
                    + " a valid company name.";

    public static final String MESSAGE_CONSTRAINTS_INVALID_LENGTH =
            "Oops! Company's name should not be more than " + MAX_NAME_LENGTH + " characters long (excluding spaces). "
                    + "Please try again with a shorter input.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(!name.isBlank(), MESSAGE_CONSTRAINTS_NON_EMPTY);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS_INVALID_REGEX);
        checkArgument(isValidNameLength(name), MESSAGE_CONSTRAINTS_INVALID_LENGTH);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid name length.
     */
    public static boolean isValidNameLength(String test) {
        return test.length() <= MAX_NAME_LENGTH;
    }


    @Override
    public String toString() {
        return fullName;
    }

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


        return sanitize(fullName).equals(sanitize(otherName.fullName));
    }

    /**
     * Sanitizes the name for comparison purposes:
     * 1. Converts to lowercase.
     * 2. Trims whitespace and reduces consecutive whitespaces to a single space.
     * 3. Removes special characters.
     * @param name Name to sanitize
     * @return Sanitized name
     */
    private String sanitize(String name) {
        if (name == null) {
            return null;
        }

        // Convert to lowercase
        name = name.toLowerCase();

        // Remove special characters, excluding spaces, alphanumeric characters
        name = name.replaceAll("[^a-zA-Z0-9 ]", "");

        // Trim whitespace and removes all whitespace between words
        name = name.trim().replaceAll("\\s+", "");

        return name;
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
