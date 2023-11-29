package seedu.address.model.treatment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a treatment's start time in the address book. Guarantees: immutable; is valid as
 * declared in {@link #isValidTime(String)}
 */
public class TreatmentTime {

    public static final String MESSAGE_CONSTRAINTS =
        "Treatment Time should only be in the following format HH:MM";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public final String value;

    /**
     * Constructs a {@code TreatmentName}.
     *
     * @param time A valid time.
     */
    public TreatmentTime(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = time;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTime(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            LocalTime.parse(test, TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
        if (!(other instanceof TreatmentTime)) {
            return false;
        }

        TreatmentTime otherTime = (TreatmentTime) other;
        return value.equals(otherTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
