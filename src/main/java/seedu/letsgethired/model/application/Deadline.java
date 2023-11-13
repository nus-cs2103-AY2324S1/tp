package seedu.letsgethired.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents an Intern Application's deadline in the intern tracker.
 * Guarantees: immutable; is always valid
 */
public class Deadline implements Comparable<Deadline> {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be a valid date in this format: dd MMM yyyy, for example, 25 Sep 2023.";

    public static final String DEFAULT_DATE = "No deadline";

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM uuuu").withResolverStyle(
                    ResolverStyle.STRICT
            );

    public final String value;

    /**
     * Constructs a {@code Deadline} object.
     *
     * @param date A valid date.
     */
    public Deadline(String date) {
        requireNonNull(date);
        checkArgument(isValidDeadline(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String date) {
        if (date.equals(DEFAULT_DATE)) {
            return true;
        }
        try {
            LocalDate.parse(date, DATE_FORMATTER);
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
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Deadline other) {
        if (this.equals(other)) {
            return 0;
        }
        if (this.value.equals(DEFAULT_DATE)) {
            return 1;
        }
        if (other.value.equals(DEFAULT_DATE)) {
            return -1;
        }
        LocalDate thisDate = LocalDate.parse(this.value, DATE_FORMATTER);
        LocalDate otherDate = LocalDate.parse(other.value, DATE_FORMATTER);
        return thisDate.compareTo(otherDate);
    }
}
