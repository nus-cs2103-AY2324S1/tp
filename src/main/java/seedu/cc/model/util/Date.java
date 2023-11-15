package seedu.cc.model.util;

import static seedu.cc.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a medical history event's date.
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Dates should be in the format YYYY-MM-DD.";
    private final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }



    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String testDate) {
        try {
            LocalDate.parse(testDate, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if the date represented by this object is in the past relative to the current system date.
     *
     * @return true if this date is before the current system date.
     */
    public boolean isFutureDate() {
        return date.isAfter(LocalDate.now());
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
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
        return date.equals(otherDate.date);
    }
}
