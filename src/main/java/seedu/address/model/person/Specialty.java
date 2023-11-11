package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Specialist's specialty in the address book.
 */
public class Specialty {
    public static final String MESSAGE_CONSTRAINTS =
            "Specialty should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the Specialty must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param specialtyInput A valid specialty.
     */
    public Specialty(String specialtyInput) {
        requireNonNull(specialtyInput);
        checkArgument(isValidSpecialty(specialtyInput), MESSAGE_CONSTRAINTS);
        value = specialtyInput;
    }

    /**
     * Returns true if a given string is a valid specialty.
     */
    public static boolean isValidSpecialty(String test) {
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
        if (!(other instanceof Specialty)) {
            return false;
        }

        Specialty otherSpecialty = (Specialty) other;
        return value.equals(otherSpecialty.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public Specialty getCopy() {
        return new Specialty(this.value);
    }
}
