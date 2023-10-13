package seedu.lovebook.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.AppUtil.checkArgument;

/**
 * Represents the date's horoscope in the lovebook book.
 */
public class Horoscope {
    public static final String MESSAGE_CONSTRAINTS =
            "Horoscope should only contain valid horoscope signs.";
    public static final String VALIDATION_REGEX = "[A-Za-z]{1,}";
    public final String value;

    /**
     * Constructs a {@code Horoscope}.
     *
     * @param horoscope A valid horoscope.
     */
    public Horoscope(String horoscope) {
        requireNonNull(horoscope);
        checkArgument(isValidHoroscope(horoscope), MESSAGE_CONSTRAINTS);
        value = horoscope;
    }

    /**
     * Returns true if a given string is a valid age.
     */
    public static boolean isValidHoroscope(String test) {
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
        if (!(other instanceof Horoscope)) {
            return false;
        }

        Horoscope otherAge = (Horoscope) other;
        return value.equals(otherAge.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
