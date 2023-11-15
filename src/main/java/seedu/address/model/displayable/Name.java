package seedu.address.model.displayable;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.AppUtil;
import seedu.address.commons.util.StringUtil;


/**
 * Represents a Displayable's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names cannot be blank";
    public static final String MESSAGE_RECOMMENDATIONS =
            "Names should contain only alphanumeric characters and spaces";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\S.*";

    public static final String AFFIRMATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        AppUtil.validateArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static boolean isAppropriateName(String test) {
        return test.matches(AFFIRMATION_REGEX);
    }

    /**
     * Checks if two names are somewhat similar to each other.
     * @param otherName the other name to check against.
     * @return whether the two names are similar. We determine similarity as requiring 2 or fewer edits
     *     to make them the same string, or if one contains the other.
     */
    public boolean isSameNameFuzzyMatch(Name otherName) {
        return (StringUtil.distanceLeven(otherName.fullName, fullName) <= 2)
                || otherName.fullName.contains(fullName)
                || fullName.contains(otherName.fullName);
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

}
