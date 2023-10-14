package seedu.address.model.event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * DateTime class to represent the date and time of an event.
 */
public class EventDate {

    private LocalDate date;

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in YYYY-mm-yy format, i.e. 2023-09-30, and it should not be blank";

    public EventDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, dateFormatter);
    }

    public static boolean isValidDate(String trimmedDate) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDate = LocalDate.parse(trimmedDate, dateFormatter);
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

    public String toString() {
        return this.date.toString();
    }
}
