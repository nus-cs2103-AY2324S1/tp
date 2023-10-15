package seedu.address.model.event;

import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * DateTime class to represent the date and time of an event.
 */
public class EventDate {

    private LocalDate date;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in YYYY-mm-yy format, i.e. 2023-09-30, and it should not be blank";

    public EventDate(String date) throws ParseException {
        try {
            this.date = LocalDate.parse(date, DATE_FORMATTER);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    public static boolean isValidDate(String trimmedDate) {
        try {
            LocalDate parsedDate = LocalDate.parse(trimmedDate, DATE_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
