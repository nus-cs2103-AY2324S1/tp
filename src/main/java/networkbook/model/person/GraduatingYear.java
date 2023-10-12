package networkbook.model.person;

import static java.util.Objects.requireNonNull;
import static networkbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's year of graduation in the network book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGraduatingYear(String)}
 */
public class GraduatingYear {


    public static final String MESSAGE_CONSTRAINTS =
            "Year of graduation (in A.D.) should only contain numbers, and it should be at least 4 digits long";
    public static final String VALIDATION_REGEX = "\\d{4,}";
    public final String value;

    /**
     * Constructs a {@code GraduatingYear}.
     *
     * @param graduatingYear A valid phone number.
     */
    public GraduatingYear(String graduatingYear) {
        requireNonNull(graduatingYear);
        checkArgument(isValidGraduatingYear(graduatingYear), MESSAGE_CONSTRAINTS);
        value = graduatingYear;
    }

    /**
     * Returns true if a given string is a valid year of graduation.
     */
    public static boolean isValidGraduatingYear(String test) {
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
        if (!(other instanceof GraduatingYear)) {
            return false;
        }

        GraduatingYear otherPhone = (GraduatingYear) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
