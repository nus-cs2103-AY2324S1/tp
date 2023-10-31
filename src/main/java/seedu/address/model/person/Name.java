package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListEntryField;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name extends ListEntryField {
    public static final Name DEFAULT_NAME = new Name();

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

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
    public Name(String name) throws ParseException {
        name = name.trim();
        requireNonNull(name);
        if (!Name.isValidName(name)) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
        fullName = name;
    }

    private Name() {
        fullName = "To be added";
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    public static Boolean isValid(String input) {
        return isValidName(input);
    }
    public static Name of(String input) throws ParseException {
        return new Name(input);
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
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    /**
     * Returns a clone of this name that is equal to this name.
     */
    public Name clone() {
        if (this == DEFAULT_NAME) {
            return DEFAULT_NAME;
        } else {
            try {
                return new Name(fullName);
            } catch (ParseException e) {
                throw new AssertionError("Clone of valid name failed.");
            }
        }
    }

    public int compareTo(Name n) {
        if (this == DEFAULT_NAME) {
            return 1;
        } else if (n == DEFAULT_NAME) {
            return -1;
        } else {
            return fullName.compareTo(n.fullName);
        }
    }

}
