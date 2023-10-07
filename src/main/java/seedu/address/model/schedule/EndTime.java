package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Schedule's end time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndTime(String)}
 */
public class EndTime {
    public static final String MESSAGE_CONSTRAINTS =
            "EndTime should only contain a valid date and time in the format 2023-09-15T09:00:00, and it should not "
                    + "be blank";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
    private final LocalDateTime time;

    /**
     * Constructs a {@code EndTime}.
     *
     * @param time A valid end time.
     */
    public EndTime(LocalDateTime time) {
        requireNonNull(time);

        this.time = time;
    }

    public static boolean isValidEndTime(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            LocalDateTime.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EndTime)) {
            return false;
        }

        EndTime otherEndTime = (EndTime) other;
        return time.equals(otherEndTime.time);
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
