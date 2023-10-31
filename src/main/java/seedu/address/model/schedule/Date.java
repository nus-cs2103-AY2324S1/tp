package seedu.address.model.schedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Schedule's end time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {
    public static final String DATE_INPUT_FORMAT = "yyyy-MM-dd";
    public static final String DATE_OUTPUT_FORMAT = "MMM d yyyy";
    public static final DateTimeFormatter DATE_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_OUTPUT_FORMAT);

    public static final String MESSAGE_CONSTRAINTS =
            "Date should only contain a valid date in the format yyyy-MM-dd"
                    + ", and it should not be blank";

    public final LocalDate value;

    /**
     * Constructs a {@code Date}.
     *
     * @param value A valid date.
     */
    public Date(LocalDate value) {
        this.value = value;
    }

    /**
     * Compares this {@code Date} object with another {@code Date} object for order.
     *
     * @param other the {@code Date} object to be compared.
     * @return a negative integer if this {@code Date} is before the other {@code Date},
     *         zero if they are equal,
     *         or a positive integer if this {@code Date} is after the other {@code Date}.
     */
    public int compareTo(Date other) {
        return value.compareTo(other.value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return value.equals(otherDate.value);
    }

    @Override
    public String toString() {
        return value.format(DATE_OUTPUT_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (!test.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }

        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
