package seedu.application.model.job;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.application.commons.util.AppUtil;

/**
 * Represents a Job's deadline in the application book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be in valid DateTime format.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static String DEFAULT_DATETIME_FORMAT = "MMM dd yyyy HHmm";
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);
    public static final String TO_ADD_DEADLINE = "TO_ADD_DEADLINE";
    public static final Deadline EMPTY_DEADLINE = new Deadline(TO_ADD_DEADLINE);

    public final String deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param dateTime A valid dateTime.
     */
    public Deadline(String dateTime) {
        requireNonNull(dateTime);
        AppUtil.checkArgument(isValidDeadline(dateTime), MESSAGE_CONSTRAINTS);

        if (isEmptyDeadline(dateTime)) {
            this.deadline = TO_ADD_DEADLINE;
        } else {
            this.deadline = dateTime;
        }
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        if (isEmptyDeadline(test)) {
            return true;
        }
        try {
            LocalDateTime dateTime = LocalDateTime.parse(test, dateTimeFormatter);
            LocalDateTime current = LocalDateTime.now();
            return dateTime.format(dateTimeFormatter).equals(test)
                    && dateTime.isAfter(current);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isEmptyDeadline(String test) {
        if (test.equals(TO_ADD_DEADLINE) || test.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmptyDeadline(deadline)) {
            return TO_ADD_DEADLINE;
        } else {
            LocalDateTime parsedDeadline = LocalDateTime.parse(deadline, dateTimeFormatter);
            return parsedDeadline.format(dateTimeFormatter);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return deadline.equals(otherDeadline.deadline);
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }


}
