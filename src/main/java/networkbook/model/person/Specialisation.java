package networkbook.model.person;

import static java.util.Objects.requireNonNull;
import static networkbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's specialisation in the network book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSpecialisation(String)}
 */
public class Specialisation {

    public static final String MESSAGE_CONSTRAINTS =
            "Specialisations can take any value, but should not be blank.\n"
            + "Additionally, only the first letter of each word may be uppercase,\n"
            + "and more than one space between each word is not allowed";

    /*
     * The first character of the specialisation must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * Proceeding letters of a word should not be uppercase, unless...
     */
    public static final String NOT_ALL_CAPITALIZED_REGEX = "^[A-Za-z0-9][a-z0-9.,_-]{0,99}$";
    /*
     * The word is in all uppercase, such as for acronyms are also acceptable.
     */
    public static final String ALL_CAPITALIZED_REGEX = "^[A-Z]+$";

    public final String specialisation;

    /**
     * Constructs an {@code Course}.
     *
     * @param specialisation A valid specialisation.
     */
    public Specialisation(String specialisation) {
        requireNonNull(specialisation);
        checkArgument(isValidSpecialisation(specialisation), MESSAGE_CONSTRAINTS);
        this.specialisation = specialisation;
    }

    /**
     * Returns true if a given string is a valid course.
     */
    public static boolean isValidSpecialisation(String test) {
        if (test.startsWith(" ")) {
            return false;
        }
        for (String word : test.split(" ")) {
            if (!(word.matches(NOT_ALL_CAPITALIZED_REGEX) || word.matches(ALL_CAPITALIZED_REGEX))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return specialisation;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Specialisation)) {
            return false;
        }

        Specialisation otherSpecialisation = (Specialisation) other;
        return specialisation.equals(otherSpecialisation.specialisation);
    }

    @Override
    public int hashCode() {
        return specialisation.hashCode();
    }

}
