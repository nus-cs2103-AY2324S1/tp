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

    public static final String DEADLINE_SPECIFIER = "-d";
    public static final String MESSAGE_CONSTRAINTS =
        "Deadline should be in valid DateTime format: "
            + "MMM dd yyyy HHmm\n"
            + "Eg. Dec 31 2030 1200";

    public static final String TO_ADD_DEADLINE = "TO_ADD_DEADLINE";
    public static final Deadline EMPTY_DEADLINE = new Deadline(TO_ADD_DEADLINE);
    public static final String DEFAULT_DATETIME_FORMAT = "MMM dd yyyy HHmm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);

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
            LocalDateTime dateTime = LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            LocalDateTime current = LocalDateTime.now();
            return dateTime.format(DATE_TIME_FORMATTER).equals(test)
                && dateTime.isAfter(current);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isEmptyDeadline(String test) {
        return test.equals(TO_ADD_DEADLINE);
    }

    /**
     * Checks if the date and time represented by this {@code Deadline} is earlier
     * than the other Deadline. Empty deadlines are considered to be earlier.
     *
     * @param other The other {@code Deadline} to be compared to.
     * @return -1 if this deadline is earlier, 0 if they are equal, and 1 if this deadline is later.
     */
    public int compareTo(Deadline other) {
        if (this.deadline.equals(TO_ADD_DEADLINE)) {
            return other.deadline.equals(TO_ADD_DEADLINE) ? 0 : -1;
        }

        if (other.deadline.equals(TO_ADD_DEADLINE)) {
            return 1;
        }

        LocalDateTime thisDateTime = LocalDateTime.parse(this.deadline, DATE_TIME_FORMATTER);
        LocalDateTime otherDateTime = LocalDateTime.parse(other.deadline, DATE_TIME_FORMATTER);

        if (thisDateTime.equals(otherDateTime)) {
            return 0;
        } else if (thisDateTime.isAfter(otherDateTime)) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        if (isEmptyDeadline(deadline)) {
            return TO_ADD_DEADLINE;
        } else {
            LocalDateTime parsedDeadline = LocalDateTime.parse(deadline, DATE_TIME_FORMATTER);
            return parsedDeadline.format(DATE_TIME_FORMATTER);
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
