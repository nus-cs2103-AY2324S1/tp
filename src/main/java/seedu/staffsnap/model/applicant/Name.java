package seedu.staffsnap.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant's name in the applicant book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name implements Comparable<Name> {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
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
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
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
     * @param o the object to be compared.
     * @return the value 0 if the argument Name is equal to this Name; a value less than 0 if this Name is
     *      lexicographically less than the Name argument; and a value greater than 0 if this string is
     *      lexicographically greater than the Name argument.
     */
    @Override
    public int compareTo(Name o) {
        return this.fullName.compareTo(o.fullName);
    }
}
