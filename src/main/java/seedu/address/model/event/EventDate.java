package seedu.address.model.event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * DateTime class to represent the date and time of an event.
 */
public class EventDate {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in YYYY-mm-yy format, i.e. 2023-09-30, and it should not be blank";

    private LocalDate date;

    /**
     * Constructs an EventDate object.
     * @param date
     * @throws ParseException if the date is invalid.
     */
    public EventDate(String date) throws ParseException {
        try {
            this.date = LocalDate.parse(date, DATE_FORMATTER);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String trimmedDate) {
        try {
            LocalDate parsedDate = LocalDate.parse(trimmedDate, DATE_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets the date of the event
     * @return date of the event
     */
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventDate)) {
            return false;
        }

        EventDate otherDate = (EventDate) other;
        return this.date.isEqual(otherDate.date);
    }

    @Override
    public String toString() {
        return this.date.format(DATE_FORMATTER);
    }

    /**
     * Returns a string representation of the date in a format for display.
     * @return a string representation of the date in a format for display.
     */
    public String forDisplay() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

    }

    public boolean isWithinDays(int days) {
        LocalDate endDate = LocalDate.now().plusDays(days + 1);
        return this.date.isBefore(endDate) && this.date.isAfter(LocalDate.now());
    }
}
