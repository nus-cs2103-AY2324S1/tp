package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Prescription's frequency in the prescription list.
 * Guarantees: immutable; is valid as declared in {@link #isValidFrequency(String)}
 */
public class Frequency {

    public static final String MESSAGE_CONSTRAINTS =
            "Frequencies should only be one of the following (Daily / Weekly / Monthly), and it should not be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(Daily|Weekly|Monthly)";

    private final String fullFrequency;

    /**
     * Constructs a {@code Frequency}.
     *
     * @param frequency A valid frequency.
     */
    public Frequency(String frequency) {
        requireNonNull(frequency);
        checkArgument(isValidFrequency(frequency), MESSAGE_CONSTRAINTS);
        fullFrequency = frequency;
    }

    /**
     * Returns true if a given string is a valid frequency.
     */
    public static boolean isValidFrequency(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getFrequency() {
        return fullFrequency;
    }


    @Override
    public String toString() {
        return fullFrequency;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Frequency)) {
            return false;
        }

        Frequency otherFrequency = (Frequency) other;
        return fullFrequency.equals(otherFrequency.fullFrequency);
    }

    @Override
    public int hashCode() {
        return fullFrequency.hashCode();
    }

}
