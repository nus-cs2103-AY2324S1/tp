package seedu.lovebook.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.AppUtil.checkArgument;

/**
 * Represents the date's gender in the lovebook book.
 */
public class Gender {
    public static final String MESSAGE_CONSTRAINTS =
            "Gender should be a single character, either M or F";
    public static final String VALIDATION_REGEX = "[MF]{1}";
    public final String value;

    /**
     * Constructs a {@code Age}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        value = gender;
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
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
        if (!(other instanceof Gender)) {
            return false;
        }

        Gender otherAge = (Gender) other;
        return value.equals(otherAge.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
