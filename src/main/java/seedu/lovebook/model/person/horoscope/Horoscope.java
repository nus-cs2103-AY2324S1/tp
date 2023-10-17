package seedu.lovebook.model.person.horoscope;

import static java.util.Objects.requireNonNull;

/**
 * Represents the date's horoscope in the lovebook book.
 */
public class Horoscope {
    public static final String MESSAGE_CONSTRAINTS =
            "Horoscope should only contain valid horoscope signs.";
    public final String value;

    /**
     * Constructs a {@code Horoscope}.
     *
     * @param horoscope A valid horoscope.
     */
    public Horoscope(String horoscope) {
        requireNonNull(horoscope);
        value = horoscope;
    }

    /**
     * Returns true if a given string is a valid horoscope.
     */
    public static boolean isValidHoroscope(String test) {
        try {
            HoroscopeEnum.valueOf(test);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
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

        Horoscope otherHoroscope = (Horoscope) other;
        return value.equals(otherHoroscope.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
