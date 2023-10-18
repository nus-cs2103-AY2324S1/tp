package seedu.address.model.schedule;

import java.time.LocalDateTime;

/**
 * Represents a Schedule's end time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndTime(String)}
 */
public class EndTime extends Time {
    public static final String MESSAGE_CONSTRAINTS =
            "EndTime should only contain a valid date and time in the format " + DATETIME_INPUT_FORMAT
                    + ", and it should not be blank";

    /**
     * Constructs a {@code EndTime}.
     *
     * @param value A valid end time.
     */
    public EndTime(LocalDateTime value) {
        super(value);
    }

    /**
     * Returns true if a given string is a valid end time.
     */
    public static boolean isValidEndTime(String test) {
        return isValidTimeString(test);
    }
}
