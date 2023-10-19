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
            + "Additionally, the specialisation cannot start with spaces or have more than 1 space between words.";

    private final String specialisation;

    /**
     * Constructs an {@code Specialisation}.
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
            if ((word.equals(""))) {
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

    public String getSpecialisation() {
        return specialisation;
    }
}
