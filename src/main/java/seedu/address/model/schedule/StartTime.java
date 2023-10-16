package seedu.address.model.schedule;

import java.time.LocalDateTime;

/**
 * Represents a Schedule's start time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
 */
public class StartTime extends Time {
    public static final String MESSAGE_CONSTRAINTS =
            "StartTime should only contain a valid date and time in the format " + DATETIME_INPUT_FORMAT
                    + ", and it should not be blank";

    /**
     * Constructs a {@code StartTime}.
     *
     * @param value A valid start time.
     */
    public StartTime(LocalDateTime value) {
        super(value);
    }

    /**
     * Returns true if a given string is a valid start time.
     */
    public static boolean isValidStartTime(String test) {
        return isValidTimeString(test);
    }
}
