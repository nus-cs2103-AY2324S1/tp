package seedu.address.model.event;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    private LocalTime eventTime;

    public EventTime(String eventTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.eventTime = LocalTime.parse(eventTime, formatter);
    }

    public static boolean isValidTime(String trimmedTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime parsedTime = LocalTime.parse(trimmedTime, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventTime)) {
            return false;
        }

        EventTime otherTime = (EventTime) other;
        return this.eventTime.equals(otherTime.eventTime);
    }
}
