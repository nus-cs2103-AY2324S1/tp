package seedu.address.model.event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * DateTime class to represent the date and time of an event.
 */
public class EventDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in YYYY-mm-yy format, i.e. 2023-09-30, and it should not be blank";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDate date;

    /**
     * Constructor for EventDate
     * @param date date of the event
     */
    public EventDate(String date) {
        this.date = LocalDate.parse(date, DATE_FORMATTER);
    }

    /**
     * Checks if the date is valid
     * @param trimmedDate date to be checked
     * @return true if the date is valid, false otherwise
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
        return this.date.toString();
    }
}
