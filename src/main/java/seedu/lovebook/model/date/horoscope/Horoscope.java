package seedu.lovebook.model.date.horoscope;

import static java.util.Objects.requireNonNull;

/**
 * Represents the date's horoscope in the LoveBook.
 */
public class Horoscope implements Comparable<Horoscope> {
    public static final String MESSAGE_CONSTRAINTS = "Horoscope should only contain valid horoscope signs.\n"
            + "If you aren't aware of Horoscope signs, please refer to the following list\n"
            + "ARIES\n"
            + "TAURUS\n"
            + "GEMINI\n"
            + "CANCER\n"
            + "LEO\n"
            + "VIRGO\n"
            + "LIBRA\n"
            + "SCORPIO\n"
            + "SAGITTARIUS\n"
            + "CAPRICORN\n"
            + "AQUARIUS\n"
            + "PISCES\n"
            + "Please try again!";
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

    @Override
    public int compareTo(Horoscope o) {
        return this.toString().toLowerCase().compareTo(o.toString().toLowerCase());
    }
}
